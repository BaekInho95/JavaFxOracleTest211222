package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {

	@FXML
	private Button btnCancel;

	@FXML
	private Button btnLogin;

	@FXML
	private TextField txtPassword;

	@FXML
	private TextField txtUserName;

	@FXML
	void cancel(ActionEvent event) {
		System.exit(0);

	}

	@FXML
	void login(ActionEvent event) {
		Connection conn;
		PreparedStatement psmt;
		ResultSet rs;
		String id = txtUserName.getText();
		String userpassword = txtPassword.getText();
		Alert message = new Alert(AlertType.WARNING);
		if (id.equals("") && userpassword.equals("")) {
			message.setContentText("id or Password Blank");
			message.show();
		} else {
			try {
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "c##ino";
				String password1 = "1234";
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, user, password1);
				psmt = conn.prepareStatement("select * from usertable where id=? and userpassword=?");
				psmt.setString(1, id);
				psmt.setString(2, userpassword);
				rs = psmt.executeQuery();
				if (rs.next()) {
					message.setContentText("로그인 성공");
					message.show();
				} else {
					message.setContentText("로그인 실패");
					message.show();
					txtUserName.setText("");
					txtPassword.setText("");
					txtUserName.requestFocus();
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
