import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Registration extends JDialog {
    private JTextField aEmail;
    private JPasswordField aPassword;
    private JPasswordField aPasswordAg;
    private JTextField aSurname;
    private JTextField aPersonID;
    private JTextField aLocation;
    private JTextField aPhone;
    private JButton registerBTN;
    private JPanel registracija;
    private JTextField aName;
    private JRadioButton genderM;
    private JRadioButton genderW;
    private JCheckBox agr;
    public String gender;

    public void getGender(){
        if(genderM.isSelected()){
            gender = "Vīrietis";
        } else {
            gender = "Sieviete";
        }
    }



    public Registration(JFrame parent) {
        super(parent);
        setTitle("Reģistrācija");
        setModal(true);
        setContentPane(registracija);
        setMinimumSize(new Dimension(650, 474));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        registerBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        setVisible(true);
    }

    public void registerUser() {
        String email = aEmail.getText();
        String password = aPassword.getText();
        String passwordAd = aPasswordAg.getText();
        String name = aName.getText();
        String surname = aSurname.getText();
        String personID = aPersonID.getText();
        String location = aLocation.getText();
        String phone = aPhone.getText();
        String genderP = gender;


        if (email.isEmpty() || password.isEmpty() || passwordAd.isEmpty() || name.isEmpty() || surname.isEmpty() || personID.isEmpty() || location.isEmpty() || phone.isEmpty() || (genderM.isSelected()==false && genderW.isSelected()==false)) {
            JOptionPane.showMessageDialog(this,
                    "Lūdzu aizpildi visus lauciņus!",
                    "Mēģini vēlreiz!",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(passwordAd)) {
            JOptionPane.showMessageDialog(this,
                    "Parole nav vienāda!",
                    "Mēģini vēlreiz!",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(!agr.isSelected()){
            JOptionPane.showMessageDialog(this,
                    "Tev ir jāpiekrīt lietošanas noteikumiem",
                    "Mēģini vēlreiz!",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        user = addUserToDB(email, password, name, surname, personID, location, phone, genderP);
        if (user != null) {
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Reģistrācija neizdevās!",
                    "Mēģini vēlreiz!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public User user;

    private User addUserToDB(String email, String password, String name, String surname, String personID, String location, String phone, String gender) {
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost:3306";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO projectRCS.users (email, password, name, surname, personID, location, phone, gender) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, surname);
            preparedStatement.setString(5, personID);
            preparedStatement.setString(6, location);
            preparedStatement.setString(7, phone);
            preparedStatement.setString(8, gender);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.email = email;
                user.password = password;
                user.name = name;
                user.surname = surname;
                user.personID = personID;
                user.location = location;
                user.phone = phone;
                user.genderQ = gender;
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;

    }
}


