/*import java.sql.SQLException;

public class Borrar {

    public static void main(String[] args) throws SQLException {
        String host = "jdbc:sqlite:src/main/resources/network";
        con = java.sql.DriverManager.getConnection(host);
        Scanner scanner = new Scanner(System.in);
        inicio();
        int option = scanner.nextInt();

        if (option == 0){
            return;
        } else if (option == 1) {
            login();
            paginaPrincipal();
        }
        option = scanner.nextInt();
        if (option == 2){
            leerPosts();
        }

        private static void inicio() throws SQLException {
            System.out.print("\n" +
                    "\n" +
                    " ________   _______  _________  ___       __   ________  ________  ___  __       \n" +
                    "|\\   ___  \\|\\  ___ \\|\\___   ___\\\\  \\     |\\  \\|\\   __  \\|\\   __  \\|\\  \\|\\  \\     \n" +
                    "\\ \\  \\\\ \\  \\ \\   __/\\|___ \\  \\_\\ \\  \\    \\ \\  \\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\/  /|_   \n" +
                    " \\ \\  \\\\ \\  \\ \\  \\_|/__  \\ \\  \\ \\ \\  \\  __\\ \\  \\ \\  \\\\\\  \\ \\   _  _\\ \\   ___  \\  \n" +
                    "  \\ \\  \\\\ \\  \\ \\  \\_|\\ \\  \\ \\  \\ \\ \\  \\|\\__\\_\\  \\ \\  \\\\\\  \\ \\  \\\\  \\\\ \\  \\\\ \\  \\ \n" +
                    "   \\ \\__\\\\ \\__\\ \\_______\\  \\ \\__\\ \\ \\____________\\ \\_______\\ \\__\\\\ _\\\\ \\__\\\\ \\__\\\n" +
                    "    \\|__| \\|__|\\|_______|   \\|__|  \\|____________|\\|_______|\\|__|\\|__|\\|__| \\|__|\n" +
                    "\n" + "_________________________________________________________________________________" + "\n" +
                    "0 Exit / 1 Login" + "\n" + "_________________________________________________________________________________"+ "\n" + "-->");
        }

        private static void login() throws SQLException {
            Scanner scanner = new Scanner(System.in);
            String user;
            System.out.println("Introduce el usuario: ");
            user = scanner.nextLine();
        }

        private static void paginaPrincipal() throws SQLException {
            System.out.print("\u001B[33m_________________________________________________________________________________" + "\n" +
                    "0 Exit / 1 Crear Post / 2 Mis Publicaciones" + "\n" + "_________________________________________________________________________________"
                    + "\n" + "-->\u001B[0m");
        }

        private static void leerPosts() throws SQLException{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM posts");
            while (rs.next()){
                System.out.printf("ID: %d, Texto: %s, Likes: %d, Fecha: %s, Usuario: %d, \n", rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
            }
        }

    }


*/