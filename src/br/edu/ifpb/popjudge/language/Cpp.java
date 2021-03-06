package br.edu.ifpb.popjudge.language;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import br.edu.ifpb.popjudge.control.TimedShell;
import br.edu.ifpb.popjudge.exception.CompilationErrorException;
import br.edu.ifpb.popjudge.exception.TimeLimitExceededException;
import br.edu.ifpb.popjudge.model.Problem;
import br.edu.ifpb.popjudge.model.Submission;

public class Cpp extends Language {

	public Cpp(int idLanguage, String name) {
		super(idLanguage, name);
	}

	@Override
	public boolean compile(Submission submission)
			throws CompilationErrorException {
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(submission.getDir() + "/compile.sh")));
			writer.write("cd \"" + submission.getDir() + "\"\n");
			writer.write("g++ -lm -std=c++11 "
					+ submission.getFile().getAbsolutePath()
					+ " -o "
					+ submission.getFile().getAbsolutePath().substring(0,
							submission.getFile().getAbsolutePath().length() - 4)
					+ " 2> " + submission.getDir() + "/errors.txt");
			writer.close();

			Process process = runtime.exec("chmod +x " + submission.getDir()
					+ "/compile.sh");
			process.waitFor();

			process = runtime.exec(submission.getDir() + "/compile.sh");
			process.waitFor();

			File file = new File(submission.getFile().getAbsolutePath().substring(0,
								 submission.getFile().getAbsolutePath().length() - 4));

			if (!file.exists()) {
				throw new CompilationErrorException("Compilation Error");
			}

			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean execute(Submission submission)
			throws TimeLimitExceededException {
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(submission.getDir() + "/run.sh")));
			Problem p = submission.getProblem();
			writer.write("cd \"" + submission.getDir() + "\"\n");
			writer.write("chroot .\n");
			writer.write(new File(submission.getFile().getAbsolutePath().substring(0,
							   submission.getFile().getAbsolutePath().length() - 4)) + " < "
					+ p.getTestCase().getAbsolutePath() + "/input.txt"
					+ " > " + submission.getDir() + "/output.txt");
			writer.close();

			Process process = runtime.exec("chmod +x " + submission.getDir()
					+ "/run.sh");
			process.waitFor();

			process = runtime.exec(submission.getDir() + "/run.sh");

			TimedShell shell = new TimedShell(process, p.getTimeLimit());
			shell.start();
			process.waitFor();

			if (shell.isTimeOut()) {
				throw new TimeLimitExceededException("Time Limit Exceeded");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}
}
