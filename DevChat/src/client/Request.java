package client;

import general.Message;


abstract class Request {
	
	abstract void respond(Message message);

}
