package Controller;

import modelo.Usuarios;
import views.Login;
import views.MenuUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class usuariosController implements ActionListener {

    private Login login;

    public usuariosController(Login login) {
        this.login = login;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nombre = login.getNombre();
        String password = login.getPassword();

        if(Usuarios.validarUsuario(nombre, password)){
            MenuUsuario menu = new MenuUsuario();
            menu.setVisible(true);
            login.dispose();
        }else{
            JOptionPane.showMessageDialog(login, "Usuario o contraseña incorrectos");
        }
    }
}
