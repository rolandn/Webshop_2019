package ClassMetier;


public class Client
{
    private int NumClient;
    private String Nom;
    private String Prenom;
    private String Adresse;
    private String Email;
    private  String Mdp;

    /**
     * propriété GET pour obtenir la valeur
     * de chaque variable.
     */

    public int getNumClient() {return NumClient;}

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getAdresse() {
        return Adresse;
    }

    public String getEmail() {
        return Email;
    }

    public String getMdp() {
        return Mdp;
    }

    /** SET pour modifier la valeur de la
     * variable.
     * @param numClient
     */
    public void setNumClient(int numClient) {
        NumClient = numClient;
    }

    public void setNom(String nom) throws ExceptionMetier
    {
        if (nom.replace(" "," ").compareTo("") == 0)
            throw new ExceptionMetier("La chaîne du nom est vide !");
        Nom = nom;
    }

    public void setPrenom(String prenom) throws ExceptionMetier
    {
        if (prenom.replace(" "," ").compareTo("") == 0)
            throw new ExceptionMetier("La chaîne du prénom est vide !");
        Prenom = prenom;
    }

    public void setAdresse(String adresse) throws ExceptionMetier
    {
        if (adresse.replace(" "," ").compareTo("") == 0)
            throw new ExceptionMetier("La chaîne de l'adresse est vide !");
        Adresse = adresse;
    }

    public void setEmail(String email) throws ExceptionMetier
    {
        if (email.indexOf('@')== -1)
            throw  new ExceptionMetier("Vous devez rentrer un e-mail valide !");
        Email = email;
    }

    public void setMdp(String mdp) throws ExceptionMetier
    {
        if (mdp.replace(" "," ").compareTo("") == 0)
            throw new ExceptionMetier("Vous devez rentrer un mot de passe !");
        Mdp = mdp;
    }

    /**
     *  CONSTRUCTEURS
     * @param
     * @param
     */

    public Client()
    {}

    public Client(Client client)
    {
        NumClient = client.NumClient;
        Nom = client.Nom;
        Prenom = client.Prenom;
        Adresse = client.Adresse;
        Email = client.Email;
        Mdp = client.Mdp;
    }

    public Client(int numClient, String nom, String prenom, String adresse, String email, String mdp)
    {
        NumClient = numClient;
        Nom = nom;
        Prenom = prenom;
        Adresse = adresse;
        Email = email;
        Mdp = mdp;
    }


}
