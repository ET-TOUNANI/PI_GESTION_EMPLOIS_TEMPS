package pi.enset.services;


import pi.enset.entities.AuthRespense;

public interface IAuth {

    public AuthRespense login(String login, String password);
    public void logout(Long id);
}
