import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Login extends JDialog {
    private JTextField email;
    private JTextField password;
    private JButton btnL;
    private JButton btnR;
    private JPanel logins;



    public Login(JFrame parent){
        super(parent);
        setTitle("Login");
        setContentPane(logins);
        setMinimumSize(new Dimension(650,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        btnL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

    }
    public static void main(String[] args) {

        }
    }



