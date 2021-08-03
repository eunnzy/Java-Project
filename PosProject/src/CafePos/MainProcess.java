package CafePos;


public class MainProcess {
	UserLogin userLogin;
	MainPos mainPos;
	
	public static void main(String[] args) {
		MainProcess main = new MainProcess();
		main.userLogin=new UserLogin();
		main.userLogin.setMain(main);
	}
	
	public void showFrameTest() {
		userLogin.dispose();
		mainPos=new MainPos();
	}

}


