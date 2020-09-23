package Model;

public class ByCountry {
    private int OBJECTID;



    private int Recovered;

    private String Country_Region;

    private int Active;



    private int Deaths;

    private int Confirmed;



    public int getOBJECTID ()
    {
        return OBJECTID;
    }

    public void setOBJECTID (int OBJECTID)
    {
        this.OBJECTID = OBJECTID;
    }



    public int getRecovered ()
    {
        return Recovered;
    }

    public void setRecovered (int Recovered)
    {
        this.Recovered = Recovered;
    }

    public String getCountry_Region ()
    {
        return Country_Region;
    }

    public void setCountry_Region (String Country_Region)
    {
        this.Country_Region = Country_Region;
    }

    public int getActive ()
    {
        return Active;
    }

    public void setActive (int Active)
    {
        this.Active = Active;
    }



    public int getDeaths ()
    {
        return Deaths;
    }

    public void setDeaths (int Deaths)
    {
        this.Deaths = Deaths;
    }

    public int getConfirmed ()
    {
        return Confirmed;
    }

    public void setConfirmed (int Confirmed)
    {
        this.Confirmed = Confirmed;
    }


}
