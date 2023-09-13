package controller;

public class ActionForward {
	
	private boolean redirect;// 리다이렉트 유무(true: redirect / false:forward)
	private String path;// 경로 : 이동할 페이지 값

	public boolean isRedirect() {
		return redirect;
	}
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
