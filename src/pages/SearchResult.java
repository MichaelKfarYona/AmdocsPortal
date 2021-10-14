package pages;

public class SearchResult {

	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	String articleTitle;
	String type;
	public SearchResult(String articleTitle, String type) {
		super();
		this.articleTitle = articleTitle;
		this.type = type;
	}
}
