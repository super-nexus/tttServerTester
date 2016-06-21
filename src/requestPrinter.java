/**
 * Created by Andrija on 6/21/16.
 */
public class requestPrinter {

    public void printRequest(Request request){

        String requestType = getRequestTypeString(request.getRequestType());

        String toPrint = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx \n"+
                         "-------------------REQUEST----------------------- \n"+
                         "requestType = " + requestType + "\n"+
                         "username = " + request.getUsername() + "\n"+
                         "password = " + request.getPassword() + "\n"+
                         "errorMessage = " + request.getErrorMessage() + "\n"+
                         "FriendInvites = " + FriendInvites(request.getFriendInvites()) + "\n"+
                         "id = " + String.valueOf(request.getId()) + "\n" +
                         "move = {" + String.valueOf(request.getMove()[0]) + " , " +  String.valueOf(request.getMove()[1]) + "}\n"+
                         "----------------------END------------------------ \n"+
                         "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";


        System.out.println(toPrint);
    }

    public String FriendInvites(String[] invites){


        String returnString = "{";

        if(invites == null){
            return "null";
        }

        for(String username : invites){

            returnString+= username + " , ";

        }

        returnString+= " }";

        return returnString;
    }

    public String getRequestTypeString(serverConstants requestType){

        switch (requestType){

            case LOGIN_REQUEST:
                return "LOGIN_REQUEST";

            case LOGOUT_REQUEST:
                return "LOGOUT_REQUEST";
            case DEAFAULT:
                return "DEFAULT";
            case ID_RECIEVED:
                return "ID_RECIEVED";
            case ERROR_MESSAGE:
                return "ERROR_MESSAGE";
            default:
                return "Default message";
        }

    }

}
