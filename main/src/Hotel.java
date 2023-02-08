public class Hotel {
    private String name;
    private String location;
    private int starCount;
    private boolean isBreakfastIncluded;
    private String optionalServices;
    public Hotel(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public boolean isBreakfastIncluded() {
        return isBreakfastIncluded;
    }

    public void setBreakfastIncluded(boolean breakfastIncluded) {
        isBreakfastIncluded = breakfastIncluded;
    }

    public String getOptionalServices() {
        return optionalServices;
    }

    public void setOptionalServices(String optionalServices) {
        this.optionalServices = optionalServices;
    }

    public Hotel(String name, String location, int starCount, boolean isBreakfastIncluded, String optionalServices) {
        this.name = name;
        this.location = location;
        this.starCount = starCount;
        this.isBreakfastIncluded = isBreakfastIncluded;
        this.optionalServices = optionalServices;
    }

    @Override
    public String toString() {
        String answer = name + " in the city " + location + "\nWith " + starCount + " stars, ";
        if(isBreakfastIncluded) answer += "with breakfast ";
        else answer += "without breakfast ";

        return answer;
    }
}
