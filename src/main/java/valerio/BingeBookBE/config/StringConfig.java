package valerio.BingeBookBE.config;

public class StringConfig {
    ///APP STRINGS
    final public static String appName = "BingeBookBE";
    final public static String appDescription = "BingeBookBE è un'applicazione web che permette di tenere traccia delle serie TV che si stanno guardando, di quelle già viste e di quelle che si vorrebbero vedere. Inoltre, permette di condividere le proprie preferenze con altri utenti e di scoprire nuove serie TV da guardare.";
    final public static String appVersion = "1.0.0";

    ///ERROR MESSAGES
    final public static String titleMessageError = "il titolo è obbligatorio";
    final public static String lastEpisodeViewed = "ultimo episodio visto è obbligatorio";
    final public static String lastEpisodeViewedSeason = "ultima stagione vista è obbligatoria";
    final public static String usernameMessageError = "Username è obbligatorio";
    final public static String emailMessageError = "Email è obbligatoria";
    final public static String passwordMessageError = "Password è obbligatoria";
    final public static String roleMessageError = "Ruolo è obbligatorio";
    final public static String nameMessageError = "Nome è obbligatorio";
    final public static String surnameMessageError = "Cognome è obbligatorio";
    final public static String genreMessageError = "Genere è obbligatorio";
    final public static String tagMessageError = "Tag è obbligatorio";
    final public static String userMessageError = "Utente è obbligatorio";
    final public static String personalDataMessageError = "Dati personali sono obbligatori";

    ///SERVICE ERROR
    final public static String errorFailedToFetchRoles = "Errore nel recupero dei ruoli";

    ///SUCCESS MESSAGES
    final public static String successCreateUser = "Utente creato con successo";
    
    ///ERROR API
    final public static String errorUploadImage = "Errore caricamento immagine";
    final public static String errorEmailAndPassword = "Email e/o password non corrispondono";
    final public static String errorNotFoundRole = "Ruolo non trovato";
    final public static String errorNotFoundTag = "Tag non trovato";
    final public static String errorNotFoundFilm = "Film non trovato";
    final public static String errorNotFoundGenre = "Genere non trovato";
    final public static String errorNotFoundUser = "Utente non trovato";
    final public static String errorAlreadyExistsUser = "Utente già esistente";
    final public static String errorAlreadyExistsGenre = "Genre già esistente";
    final public static String errorAlreadyExistsTag = "Tag già esistente";
    final public static String errorAlreadyExistsRole = "Role già esistente";
    final public static String errorNotFoundPersonalData = "Dati personali non trovati";
    final public static String errorNotFoundSerie = "Serie non trovata";
    final public static String errorNotFoundEpisode = "Episodio non trovato";
    final public static String errorNotFoundSeason = "Stagione non trovata";
    final public static String errorInsertData = "Errore inserimento dati";
    final public static String errorUnauthorized = "Accesso non autorizzato";
}