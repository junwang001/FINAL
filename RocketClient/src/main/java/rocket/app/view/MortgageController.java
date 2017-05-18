package rocket.app.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketData.LoanRequest;
import util.NumberUtil;

public class MortgageController {

	@FXML
	private TextField txtIncome;

	@FXML
	private TextField txtExpenses;

	@FXML
	private TextField txtCreditScore;

	@FXML
	private TextField txtHouseCost;

	@FXML
	private ComboBox<Integer> cmbTerm;

	@FXML
	private Label lblMortgagePayment;

	private MainApp mainApp;

	@FXML
	private void initialize() {
		ObservableList<Integer> cmbTermList = FXCollections.observableArrayList();
		cmbTermList.addAll(15, 30);
		cmbTerm.setItems(cmbTermList);
		lblMortgagePayment.setText("");
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}


	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		if((NumberUtil.isFloat(txtIncome.getText()) || NumberUtil.isInt(txtIncome.getText()))
			&& NumberUtil.isFloat(txtExpenses.getText()) || NumberUtil.isInt(txtExpenses.getText())
			&& NumberUtil.isInt(txtCreditScore.getText())
			&& NumberUtil.isInt(txtHouseCost.getText())
			&& cmbTerm.getValue() != null) {
			LoanRequest lRequest = new LoanRequest();
			lRequest.setIncome(Double.valueOf(txtIncome.getText()));
			lRequest.setExpenses(Double.valueOf(txtExpenses.getText()));
			lRequest.setiCreditScore(Integer.valueOf(txtCreditScore.getText()));
			lRequest.setiDownPayment(Integer.valueOf(txtHouseCost.getText()));
			lRequest.setiTerm(cmbTerm.getValue());
			mainApp.messageSend(lRequest);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Message Here...");
			alert.setHeaderText("Look, an error Dialog");
			alert.setContentText("input error, please check");
			alert.showAndWait().ifPresent(rs -> {
				if (rs == ButtonType.OK) {
					System.out.println("Pressed OK.");
				}
			});
		}
	}

	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		double piti = Double.valueOf(NumberUtil.format((lRequest.getIncome()- lRequest.getExpenses()) * 0.28 ));
		double pmt = lRequest.getdPayment();
		int subtract = (int)(pmt - piti) * 100;
		if(subtract > 0) {
			lblMortgagePayment.setText("House Cost too high");
		} else {
			lblMortgagePayment.setText("You can pay for it");
		}
	}

}
