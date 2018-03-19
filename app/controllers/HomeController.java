package controllers;

import javax.inject.Inject;
import javax.inject.Singleton;

import akka.stream.javadsl.*;
import akka.actor.AbstractActor;
import akka.actor.ActorSystem;
import akka.stream.Materializer;
import play.libs.streams.ActorFlow;
import play.mvc.*;
import actors.MyWebSocketActor;
import play.Logger;
/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */


public class HomeController extends Controller {

    static public  int toto;
	private final ActorSystem actorSystem;
    private final Materializer materializer;

    @Inject
    public HomeController(ActorSystem actorSystem, Materializer materializer) {
        this.actorSystem = actorSystem;
        this.materializer = materializer;
    }
	
    public WebSocket ws() {
    	
    	toto=toto+1;
    	//test
    	Logger.debug("WebSocket WS. + toto:"+toto);
        
    	
        return WebSocket.Text.accept(request ->
                ActorFlow.actorRef(MyWebSocketActor::props,
                    actorSystem, materializer
                )
        );
    }
	
    public WebSocket socket() {
        return WebSocket.Text.accept(request -> {
           
        	Logger.debug("Before Sink  ");
        	
        	Sink<String, ?> sink;
        	
        	
        	
        	sink= Sink.foreach(message -> { Logger.debug("socket sink  + MCA:"+message);});
        	
        	Logger.debug("After Sink  ");
        	
        	Logger.debug("Before Flow  ");
        	
        	//Flow flow2=Flow.
        	
        	Flow flow = Flow.<String>create().map(s -> {
        		                                        
        		                                        Logger.debug("_____socket flow  + MCA:"+s);
        		                                        return (s.toUpperCase());
        	 											});

            // log the message to stdout and send response back to client
            /*return Flow.<String>create().map(msg -> {
							                System.out.println(msg);
							                return "I received your message: " + msg;
            								});*/
        	Logger.debug("After Flow ");
        	
        	return flow;
           });
    }
    
	/**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render());
    }
    
    public Result webSocket() {
        return ok(views.html.webSocket.render());
    }
    
    
    

}
