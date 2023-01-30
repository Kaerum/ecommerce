package cliente.javafx.homePage;

import cliente.servicos.AuthenticationService;
import compartilhado.ContextoAutenticado;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.Optional;

import static cliente.Defaults.DEFAULT_PADDING;

public class HomePageView extends VBox {

    private HomePageEstado estado = HomePageEstado.LoggedOut;
    private final Button registrarButton = new Button("Registrar");
    private final Button loginButton = new Button("Logar");
    private final Label bemvindoLabel = new Label("Olá %s");
    private final Button logoutButton = new Button("Sair");
    private final HBox containerAutenticacao;
    private final HomePageViewModel viewModel = new HomePageViewModel();
    public HomePageView() {
        Label logoLabel = new Label("Ecommerce");
        HBox logoBoxContainer = new HBox(logoLabel);
        containerAutenticacao = new HBox(this.loginButton, this.registrarButton);
        HBox.setHgrow(containerAutenticacao, Priority.ALWAYS);
        HBox topBar = new HBox(logoBoxContainer, containerAutenticacao);
        HBox.setHgrow(topBar, Priority.ALWAYS);
        topBar.setMinWidth(-1);
        topBar.setMaxWidth(-1);
        topBar.setPrefWidth(-1);
        topBar.setPadding(new Insets(DEFAULT_PADDING));
        VBox homePageViewVBox = new VBox(topBar);
        getChildren().addAll(homePageViewVBox);
        initHandlersAndObservers();
    }

    private void initHandlersAndObservers() {
        AuthenticationService.instance.onContextoAutenticadoChanges(this::contextoAutenticadoMudou);
        loginButton.setOnAction(viewModel::onLoginButtonPressed);
        logoutButton.setOnAction(viewModel::onLogoutButtonPressed);
        registrarButton.setOnAction(viewModel::onRegisterButtonPressed);
    }

    private void contextoAutenticadoMudou(Optional<ContextoAutenticado> contextoAutenticadoOptional) {
        contextoAutenticadoOptional.ifPresentOrElse(
                this::changeToLoggedIn,
                this::changeToLoggedOut
        );
    }

    public void changeToLoggedIn(ContextoAutenticado contextoAutenticado) {
        bemvindoLabel.setText(String.format("Olá %s", contextoAutenticado.getNomeUsuario()));
        if (estado == HomePageEstado.LoggedOut) {
            containerAutenticacao.getChildren().removeAll(loginButton, registrarButton);
            containerAutenticacao.getChildren().addAll(bemvindoLabel, logoutButton);
            estado = HomePageEstado.LoggedIn;
        }
    }
    public void changeToLoggedOut() {
        if (estado == HomePageEstado.LoggedIn) {
            containerAutenticacao.getChildren().removeAll(bemvindoLabel, logoutButton);
            containerAutenticacao.getChildren().addAll(loginButton, registrarButton);
            estado = HomePageEstado.LoggedOut;
        }
    }
}
