package rn2012a.service;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageEncoder;

import rn2012a.frm.GeneralFrame;

public class Rn2012aCodecFactory extends DemuxingProtocolCodecFactory {
	private MessageDecoder decoder;
	private MessageEncoder<GeneralFrame> encoder;
	
	
	public Rn2012aCodecFactory(MessageDecoder decoder, MessageEncoder<GeneralFrame> encoder) {
		this.decoder = decoder;
		this.encoder = encoder;
		addMessageDecoder(this.decoder);
		addMessageEncoder(GeneralFrame.class, this.encoder);
	}
	

	public Rn2012aCodecFactory() {
//		this(Charset.forName("UTF-8"));
//		
//		addMessageDecoder(this.decoder);
//		addMessageEncoder(GeneralFrame.class, this.encoder);
	}

//	public Rn2012aCodecFactory(Charset charset) {
//		this.decoder = new Rn2012aDecoder(charset);
//		this.encoder = new Rn2012aEncoder(charset);
//		addMessageDecoder(this.decoder);
//		addMessageEncoder(GeneralFrame.class, this.encoder);
//	}

}
