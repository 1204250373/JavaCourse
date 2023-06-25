package beans;

public class Logs {
    String userId;
    String times;
    String logContext;

    public Logs() {

    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getLogContext() {
        return logContext;
    }

    public void setLogContext(String logContext) {
        this.logContext = logContext;
    }

    public Logs(String userId,String times,String logContext){
        super();
        this.userId = userId;
        this.times = times;
        this.logContext = logContext;
    }
}
