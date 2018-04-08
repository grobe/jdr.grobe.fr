package controllers;

import javax.inject.Inject;
import javax.inject.Singleton;

import actors.MyChatActorWebRTC;
import actors.MyWebSocketActor1;
import akka.actor.ActorSystem;
import akka.stream.Materializer;
import play.Logger;
import play.libs.streams.ActorFlow;
import play.mvc.*;

public class WebsocketController extends Controller {

	 static public  int toto;
		private final ActorSystem actorSystem;
	    private final Materializer materializer;

	@Singleton
	@Inject
	    public WebsocketController(ActorSystem actorSystem, Materializer materializer) {
	        this.actorSystem = actorSystem;
	        this.materializer = materializer;
	    }


    public WebSocket wsMyChatPeerToPeer() {
    	
    	toto=toto+1;
    	//test
    	Logger.debug("wsMyChatPeerToPeer WS. + toto:"+toto);
    	Logger.debug("wsMyChatPeerToPeer WS. + actorSystem="+actorSystem.name());
    	
        return WebSocket.Text.accept(request ->
                ActorFlow.actorRef(MyChatActorWebRTC::props, actorSystem, materializer)
                                  );
    }
	
	
	
	
	public Result webSocketChatPeerToPeer() {
        return ok(views.html.webChatPeerToPeer.render());
    }
    



}
