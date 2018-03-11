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

@Singleton
public class HomeController extends Controller {

        int toto;
	private final ActorSystem actorSystem;
    private final Materializer materializer;

    @Inject
    public HomeController(ActorSystem actorSystem, Materializer materializer) {
        this.actorSystem = actorSystem;
        this.materializer = materializer;
    }
	
    public WebSocket ws() {
    	
    	toto=toto++;
    	//test
    	Logger.debug("WebSocket WS. + toto:"+toto);
    	
        return WebSocket.Text.accept(request ->
                ActorFlow.actorRef(MyWebSocketActor::props,
                    actorSystem, materializer
                )
        );
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
    
    
    
    

}
