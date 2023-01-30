package cliente.javafx.dialogs.login;

import compartilhado.TipoRespostaServidor;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class LoginDialogView extends Dialog<Boolean> {
    private final TextField tfUsuario = new TextField();
    private final LoginDialogViewModel viewModel = new LoginDialogViewModel();
    public LoginDialogView() {
        Label usuarioLabel = new Label("Usuário");
        VBox container = new VBox(usuarioLabel, tfUsuario);
        container.setPadding(new Insets(40.0d));
        container.setSpacing(10.0d);
        DialogPane dp = getDialogPane();
        setTitle("Login");
        setResultConverter(this::formResult);
        ButtonType saveButton = new ButtonType("Logar", ButtonBar.ButtonData.OK_DONE);
        dp.getButtonTypes().addAll(saveButton, ButtonType.CANCEL);
        dp.setContent(container);
    }

    private Boolean formResult(ButtonType button) {
        if (button.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            var resultado = viewModel.logar(tfUsuario.getText());
            if (resultado.tipo() == TipoRespostaServidor.Sucesso && resultado.valor().isPresent()) {
                return true;
            } else {
                new Alert(Alert.AlertType.ERROR, resultado.mensagemDeErro().get()).show();
            }
        }
        return null;
    }
}
