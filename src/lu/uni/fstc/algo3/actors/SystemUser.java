package lu.uni.fstc.algo3.actors;

/**
 * A common interface? Login, that kind of stuff (not relevant for this project though).
 * Some of the functionality could be called through actor based interfaces, but for the scope of project
 * the same functionality can be demonstrated directly without distinction of actors.
 * Created by Gatis on 27/03/2015.
 */
public interface SystemUser {
    boolean login(String username, String password);
}
