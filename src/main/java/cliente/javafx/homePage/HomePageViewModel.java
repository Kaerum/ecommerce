package cliente.javafx.homePage;

import cliente.javafx.dialogs.login.LoginDialogView;
import cliente.javafx.dialogs.registrar.RegistrarDialogView;
import cliente.servicos.AuthenticationService;
import javafx.event.ActionEvent;

public class HomePageViewModel {

    public void onLoginButtonPressed(ActionEvent actionEvent) {
        LoginDialogView dialog = new LoginDialogView();
        dialog.showAndWait();
    }

    public void onLogoutButtonPressed(ActionEvent actionEvent) {
        AuthenticationService.instance.deslogar();
    }
    public void onRegisterButtonPressed(ActionEvent actionEvent) {
        RegistrarDialogView dialog = new RegistrarDialogView();
        dialog.showAndWait();
    }
}
