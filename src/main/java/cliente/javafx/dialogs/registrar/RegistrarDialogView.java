package cliente.javafx.dialogs.registrar;

import compartilhado.TipoRespostaServidor;
import compartilhado.usuario.TipoUsuario;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class RegistrarDialogView extends Dialog<Boolean> {
    private final TextField tfUsuario = new TextField();
    private final ChoiceBox<TipoUsuario> cbTipoUsuario = new ChoiceBox<>();
    private final RegistrarDialogViewModel viewModel = new RegistrarDialogViewModel();
    public RegistrarDialogView() {
        Label usuarioLabel = new Label("Usuário");
        Label tipoUsuarioLabel = new Label("Tipo de usuário");
        cbTipoUsuario.getItems().addAll(TipoUsuario.Administrador, TipoUsuario.Cliente);
        cbTipoUsuario.setValue(TipoUsuario.Cliente);
        VBox container = new VBox(
                usuarioLabel, tfUsuario,
                tipoUsuarioLabel, cbTipoUsuario
        );
        container.setPadding(new Insets(40.0d));
        container.setSpacing(10.0d);
        DialogPane dp = getDialogPane();
        setTitle("Cadastro usuário");
        setResultConverter(this::formResult);
        ButtonType saveButton = new ButtonType("Cadastrar", ButtonBar.ButtonData.OK_DONE);
        dp.getButtonTypes().addAll(saveButton, ButtonType.CANCEL);
        dp.setContent(container);
    }

    private Boolean formResult(ButtonType button) {
        if (button.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            var nomeUsuario = tfUsuario.getText();
            var resultado = viewModel.registrar(nomeUsuario, cbTipoUsuario.getValue());
            if (resultado.tipo() == TipoRespostaServidor.Sucesso && resultado.valor().isPresent()) {
                new Alert(Alert.AlertType.INFORMATION, String.format("Usuário %s cadastrado.", nomeUsuario)).show();
                return true;
            } else {
                new Alert(Alert.AlertType.ERROR, resultado.mensagemDeErro().get()).show();
            }
        }
        return null;
    }
}
