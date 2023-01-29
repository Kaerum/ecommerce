package cliente.prod.paineis;

public abstract class CustomPanel extends javax.swing.JPanel {
    public CustomPanel() {
        super(new java.awt.BorderLayout());
    }
    protected abstract void addComponents();


}
