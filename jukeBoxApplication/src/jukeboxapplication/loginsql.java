package jukeboxapplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



public class loginsql {
    ArrayList<user> users = new ArrayList<user>();
    ArrayList<music> musics = new ArrayList<>();
    
    private String dbusername = "root" ;
    private String dbpass = "1906Berk" ;
    
    private String dbname = "jukeboxloginschema";
    private String host = "localhost";
    
    private int port = 3306 ; 
    
    private Connection con  = null ;
    private Statement statment = null ;
    
    public void addcustomer(String userName , String pass){
        try {
            statment = con.createStatement();
            
            String sqlsays = "insert into login values('" +userName+"',"+pass+")";
            
            statment.executeUpdate(sqlsays);
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(loginsql.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public void userinfo(ArrayList users){
        String sqlsays = "Select *from login ";
        try {
            statment = con.createStatement();
            ResultSet rs = statment.executeQuery(sqlsays);
            
            while (rs.next()){
                String userName = rs.getString("userName");
                String pass = rs.getString("pass");
                
                user user = new user(userName, pass);
                users.add(user);
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(loginsql.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    public void musicinfo(ArrayList musics){
        String sqlsays = "Select * from musics ";
        try {
            statment = con.createStatement();
            ResultSet rs = statment.executeQuery(sqlsays);
            
            while(rs.next()){
                String songName = rs.getString("songName");
                String artistName = rs.getString("artistName");
                String musicType = rs.getString("musicType");
                String musicPath =rs.getString("musicPath");
                
                
                
                music music = new music(songName,artistName,musicType,musicPath);
                musics.add(music);
      
            }
        } catch (SQLException ex) {
            Logger.getLogger(loginsql.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    public void searchmusic(String musicname , String artistName , String musicType , ArrayList searchmusics){
        String sqlSays = "Select * from musics " ;
        int a = 0 ;
        if(musicType != "Every Tpye"){
            sqlSays += " where musicType = '" + musicType + "' " ; 
            a++ ; 
        }
        if(musicname.length() != 0 ){
            if(a==0){
                if(musicname.length() != 0 ){
                sqlSays += " where songName = '" + musicname + "'  "; 
                a++;  
                }     
            }
            else{
                sqlSays += " and songName = '" + musicname + "'  "; 
            }
        }    
        if(artistName.length() != 0 ){
            if(a==0)
                sqlSays += "where artistName = '" + artistName + "' " ; 
            else
                sqlSays += "and artistName = '" + artistName + "' " ; 
        }
        sqlSays += " ; " ; 
        try {
            System.out.println(sqlSays);
            statment = con.createStatement();
            ResultSet rs = statment.executeQuery(sqlSays);
            
            while(rs.next()){
                String songName = rs.getString("songName");
                String artistname = rs.getString("artistName");
                String musictype = rs.getString("musicType");
                String musicPath = rs.getString("musicPath");
                
             
                music music = new music(songName,artistname,musictype,musicPath);
                searchmusics.add(music);
      
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void showplaylist(ArrayList<music> arr , user user ){
        String sqlSays =  "select m.songName , m.artistName ,  m.musicType , m.musicPath from musics m , playlists  p  where p.userName = BINARY'" + 
                user.getUserName() + "'  and p.songName = m.songName ;"; 
        try { 
            statment = con.createStatement();
            ResultSet rs = statment.executeQuery(sqlSays);
            
            while(rs.next()){
                String songName = rs.getString("songName");
                String artistname = rs.getString("artistName");
                String musictype = rs.getString("musicType");
                String musicPath = rs.getString("musicPath");
                
             
                music music = new music(songName,artistname,musictype , musicPath );
                arr.add(music);
      
            }
        } catch (SQLException ex) {
            System.out.println("aaa");
        } 
    }
    
    public void addsongtoplaylist(user user , music music){
        String sqlsays = "insert into playlists values('" + user.getUserName() + "' , '" + music.getMusicName() + "') ; "  ;
        try {
            statment = con.createStatement();

            statment.executeUpdate(sqlsays);
            
           
        } catch (SQLException ex) {
            Logger.getLogger(loginsql.class.getName()).log(Level.SEVERE, null, ex);
        }          
    }
    public void deletemusicfromplaylist(music music , user user) {
        String sqlsays = "delete from playlists where songName = '" + music.getMusicName() + "' and userName = BINARY '" + user.getUserName() + "' ;" ; 
        try {
            statment = con.createStatement();
            statment.executeUpdate(sqlsays);
        } catch (Exception e) {
            System.out.println(sqlsays);
            System.out.println(e);
        }
        
    }
    
    public void deletesongfromdatabase(user user , music music ){
        deletemusicfromplaylist(music, user);
        String sqlsays = "delete from musics where songName =  '" + music.getMusicName() + "' ; " ; 
        try {
            statment = con.createStatement();
            statment.executeUpdate(sqlsays);
            
        } catch (Exception e) {
            
            System.out.println(e);
        }
    }
    
    public void addnewsongfordatabase(music music){
        a();
        String sqlsays =" insert into musics values('" + music.getMusicName() + "','" + music.getMusicName() + "','" + music.getMusicType() + " ' , '" + 
                music.getMusicPath() + "' );";
        
        try{
            System.out.println(sqlsays);
            System.out.println(music.getMusicPath());
            statment = con.createStatement();
            
            statment.execute( sqlsays);
        } catch(Exception e){
            System.out.println(e);
        } 

    }
    
   public void a(){
       String a = "SET @old_sql_mode=@@sql_mode;" ; 
        try{
           
            statment = con.createStatement();
            statment.execute(a);
            
        } catch(Exception e){
            System.out.println(e);
        } 
   } 
    
  
    public loginsql( ){
        a();
        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbname;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("we dont connect to databse");
        }
        try {
            con = DriverManager.getConnection(url,dbusername,dbpass);
            System.out.println("We are online in database");
        } catch (SQLException ex) {
            System.out.println("Connection lost");
        }
        
   }

    
}
