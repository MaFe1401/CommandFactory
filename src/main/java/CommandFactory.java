import java.util.HashMap;

public class CommandFactory {
    //Si el comando no existe en la tabla de Hash, se genera aqui
    public static Command getCommand2(String name) {
        Command cmd = null;
        Class c=null;
        try {
            c = Class.forName(name);
            cmd = (Command) c.newInstance();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        return cmd;
    }
    //Patrón Singleton con Tabla de Hash
    private HashMap<String, Command> data;
    private static CommandFactory instance;
    private CommandFactory(){
        this.data=new HashMap<String, Command>();
    }
    public static CommandFactory getInstance()
    {
        if(instance==null)instance=new CommandFactory();
        return instance;
    }
   public Command getCom(String name){
        Command c=this.data.get(name);
        if(c==null){
            c=getCommand2(name);
            this.data.put(name,c);
        }
        return c;
   }

    public static void main(String[] args){
        Command com=getInstance().getCom("C1");
        com.execute();
    }
}

