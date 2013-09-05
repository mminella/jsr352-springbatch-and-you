package org.springframework.batch.jsr.chunk;

import java.util.List;

import javax.batch.api.chunk.AbstractItemReader;

public class SkippableListItemReader extends AbstractItemReader {

	private List<String> items;
	private int failIndex = -1;
	private int index = -1;

	public SkippableListItemReader(List<String> items, int failIndex) {
		this.items = items;
		this.failIndex = failIndex;
	}

	@Override
	public Object readItem() throws Exception {
		Object item = null;
		index++;

		if(index == failIndex) {
			throw new RuntimeException("Expected...should be skipped");
		} else if(index < items.size()){
			item = items.get(index);
		}

		return item;
	}
}
