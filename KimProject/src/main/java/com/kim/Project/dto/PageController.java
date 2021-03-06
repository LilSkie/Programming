package com.kim.Project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class PageController {
	@Getter
	@Setter
	@ToString
	public class PageDTO {
		private int page;
		private int maxPage;
		private int startPage;
		private int endPage;
		private int startRow;
		private int endRow;
	}

}
