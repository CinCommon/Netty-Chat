package cc.stevenyin.netty.demo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * ��ʼ����, channelע���, ��ִ����Ӧ�ĳ�ʼ������
 * @author Steven.Yin
 *
 */
public class HelloServerInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		// ͨ��Channel��ȡ��Ӧ��pipeline
		ChannelPipeline channelPipeline = channel.pipeline();
		// ͨ��pipeline�ܵ����Handler, �������Ϊ������
		// �����󵽷����, ������Ҫ������; ��Ӧ���ͻ�����Ҫ������
		channelPipeline.addLast("HttpServerCodec", new HttpServerCodec());
		channelPipeline.addLast("HelloNettyHandler", new HelloNettyHandler());
	}

}
