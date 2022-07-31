package model;

public class Client {
    public int id;
    public int arrival_time;
    public int service_time;

    public Client(int id, int arrival_time, int service_time) {
        this.id = id;
        this.arrival_time = arrival_time;
        this.service_time = service_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(int arrival_time) {
        this.arrival_time = arrival_time;
    }

    public int getService_time() {
        return service_time;
    }

    public void setService_time(int service_time) {
        this.service_time = service_time;
    }
}
