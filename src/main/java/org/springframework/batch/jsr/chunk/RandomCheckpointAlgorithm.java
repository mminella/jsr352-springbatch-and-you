package org.springframework.batch.jsr.chunk;

import java.util.Random;

import javax.batch.api.chunk.CheckpointAlgorithm;
import javax.batch.runtime.context.StepContext;
import javax.inject.Inject;

public class RandomCheckpointAlgorithm implements CheckpointAlgorithm {

	@Inject
	public StepContext stepContext;

	@Override
	public int checkpointTimeout() throws Exception {
		return 0;
	}

	@Override
	public void beginCheckpoint() throws Exception {
	}

	@Override
	public boolean isReadyToCheckpoint() throws Exception {
		return (new Random().nextInt() % 2) == 0;
	}

	@Override
	public void endCheckpoint() throws Exception {
	}
}
