package org.springframework.batch.jsr.chunk;

import java.util.List;

import javax.batch.api.chunk.AbstractItemReader;

public class RetryableListItemReader extends AbstractItemReader {

	private List<String> items;
	private int failIndex = -1;
	private int index = 0;
	private boolean exceptionThrown = false;

	public RetryableListItemReader(List<String> items, int failIndex) {
		this.items = items;
		this.failIndex = failIndex;
	}

	@Override
	public Object readItem() throws Exception {
		Object item = null;

		if(index == failIndex && !exceptionThrown) {
			exceptionThrown = true;
			throw new RuntimeException("Expected...should be retried");
		} else if(index < items.size()){
			item = items.get(index);
			index++;
		}

		return item;
	}
}
