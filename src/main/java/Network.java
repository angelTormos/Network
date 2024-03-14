import java.sql.*;
import java.util.Scanner;

public class Network {
    private static  java.sql.Connection con;
    private static int currentScreen = 0;
    public static int userID;
    public static String userName;
    public static String comment;
    public static void main(String[] args) throws SQLException {
        int option;
        String host = "jdbc:sqlite:src/main/resources/network";
        con = java.sql.DriverManager.getConnection(host);
        while (true){
            printMenu();
            option = getOption();
            if (option == 0) break;
            if (currentScreen == 0){
                switch (option){
                    case 2: login();
                        break;
                    case 1: allPosts();
                        break;
                    case 3: register();
                        break;
                }
            }else {
                switch (option){
                    case 1: myPosts();
                        break;
                    case 2: newPost();
                        break;
                    case 3: comment();
                        break;
                    case 4: like();
                        break;
                    case 5: otherPosts();
                        break;
                    case 6:  logout();
                        break;
                }
            }
        }
    }

    private static int getOption(){
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        try {
            option = Integer.parseInt(scanner.next());
            if ((currentScreen == 0 && option > 3) || (currentScreen == 1 && option > 6)){
                System.out.println("Incorrect option");
            }
        }catch (IllegalArgumentException iae){
            System.out.println("Incorrect Option");
        }
        return option;
    }

    private static void printMenu(){
        System.out.println("_________________________________________________________________________________");
        if (currentScreen == 0){
            System.out.println("0 Exit / 1 All Posts / 2 Login / 3 Register");
        } else if (currentScreen == 1) {
            System.out.println("0 Exit / 1 My Posts / 2 New Post / 3 New Comment / 4 Like / 5 Other Posts / 6 LogOut " + userName);
        }
        System.out.println("_________________________________________________________________________________");
    }

    private static void login() throws SQLException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Name:");
        String name = scanner.nextLine();
        PreparedStatement st = null;
        String query = "SELECT * FROM usuarios WHERE nombre = ?";
        st = con.prepareStatement(query);
        st.setString(1, name);
        ResultSet rs = st.executeQuery();
        if (rs.next()){
            userID = rs.getInt("id");
            userName = rs.getString("nombre");
        }else {
            System.out.println("User not found");
        }
        currentScreen = 1;
    }

    private static void logout(){
        currentScreen = 0;
    }

    private static void allPosts() throws SQLException {
        PreparedStatement st = null;
        String query = "SELECT * FROM posts";
        st = con.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        while (rs.next()){
            System.out.println(rs.getInt("id") + " - " + rs.getString("texto"));
        }
    }

    private static void register() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        PreparedStatement st = null;
        System.out.println("Nombre:");
        String nombre = scanner.nextLine();
        System.out.println("Apellidos:");
        String apellidos = scanner.nextLine();
        String query = "INSERT INTO usuarios (nombre, apellidos) VALUES (?, ?)";
        st = con.prepareStatement(query);
        st.setString(1, nombre);
        st.setString(2, apellidos);
        st.executeUpdate();
    }

    private static void myPosts() throws SQLException {
        PreparedStatement st = null;
        String query = "SELECT * FROM posts WHERE id_usuario = ?";
        st = con.prepareStatement(query);
        st.setInt(1, userID);
        ResultSet rs = st.executeQuery();
        while (rs.next()){
            System.out.println(rs.getInt("id") + " - " + rs.getString("texto"));
        }
    }
    private static void otherPosts() throws SQLException {
        PreparedStatement st = null;
        String query = "SELECT * FROM posts WHERE id_usuario != ?";
        st = con.prepareStatement(query);
        st.setInt(1, userID);
        ResultSet rs = st.executeQuery();
        while (rs.next()){
            System.out.println(rs.getInt("id") + " - " + rs.getString("texto") + " - " + rs.getInt("likes") + " - " + rs.getString(""));
        }
    }

    private static void newPost() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        PreparedStatement st = null;
        System.out.println("Texto:");
        String texto = scanner.nextLine();
        System.out.println("El post de ha subido correctamente");
        String query = "INSERT INTO posts (texto, likes, id_usuario) VALUES (?, ?, ?)";
        st = con.prepareStatement(query);
        st.setString(1, texto);
        st.setInt(2, 0);
        st.setInt(3, userID);
        st.executeUpdate();
    }

    private static void comment() throws SQLException {
        int postID;
        Scanner scanner = new Scanner(System.in);
        otherPosts();
        System.out.println("Introduce el id del post:");
        postID = Integer.parseInt(scanner.nextLine());
        System.out.println("Comment");
        comment = scanner.nextLine();
        System.out.println("El comentario se ha subido correctamente");
        PreparedStatement st = null;
        String query = "INSERT INTO comentarios (texto, id_usuario, id_post) VALUES (?, ?, ?)";
        st = con.prepareStatement(query);
        st.setString(1, comment);
        st.setInt(2, userID);
        st.setInt(3, postID);
        st.executeUpdate();
    }

    private static void like() throws SQLException {
        int postID;
        Scanner scanner = new Scanner(System.in);
        otherPosts();
        System.out.println("Post id:");
        postID = Integer.parseInt(scanner.nextLine());
        System.out.println("LIKE!");
        PreparedStatement st = null;
        String query = "UPDATE posts SET likes = likes + 1 WHERE id = ?";
        st = con.prepareStatement(query);
        st.setInt(1, postID);
        st.executeUpdate();
    }
}