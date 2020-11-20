package kr.or.ddit.common.model;

public class PageVO {
	int page;
	int pageSize;
	String searchType;
	String keyword;
	
	//인자있는 생성자를 선언하게 되면 default 생성자(인자없는 생성자)가 생성안된다.
	//대다수 framework에서는 인자가 없는 생성자를 요구한다.
	//framework에서 해당 클래스의 인스턴스를 생성해야하는데 인자가 있으면 어떻게 생성해야하는지 알수없는 경우가 대부분
	//spring, mybatis를 사용할 경우 기본생성자생성 코드를 작성해야한다.
	//** 인자가 있는 생성자를 만들경우, 기본 생성자를 개발자가 생성
	//** 별도의 생성자를 만들지 않을 경우, jdk가 자동으로 기본 생성자를 만들어준다.
	
	public PageVO() {
		
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
		result = prime * result + page;
		result = prime * result + pageSize;
		result = prime * result + ((searchType == null) ? 0 : searchType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PageVO other = (PageVO) obj;
		if (keyword == null) {
			if (other.keyword != null)
				return false;
		} else if (!keyword.equals(other.keyword))
			return false;
		if (page != other.page)
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (searchType == null) {
			if (other.searchType != null)
				return false;
		} else if (!searchType.equals(other.searchType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PageVO [page=" + page + ", pageSize=" + pageSize + ", searchType=" + searchType + ", keyword=" + keyword
				+ "]";
	}

	public PageVO(int page, int pageSize, String searchType, String keyword) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.searchType = searchType;
		this.keyword = keyword;
	}

	public PageVO(int page, int pageSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
	}
	
	
	
	
	
}
