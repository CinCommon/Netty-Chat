package cc.stevenyin.netty.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HelloServer {

	public static void main(String[] args) {
//		1. ����һ�������߳���
		// �������߳���, ���ڽ��տͻ��˵�����
		EventLoopGroup bossGrp = new NioEventLoopGroup();
		// ������߳���, bossGrp�Ὣ���ӷַ���worker�߳�����ִ��
		EventLoopGroup workerGrp = new NioEventLoopGroup();
//		2. ���������������
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		serverBootstrap
			// �������߳������serverBootstrap��
//		4. ���ô�����̳߳ص��������ʼ����
			.group(bossGrp, workerGrp)
//		3. Ϊ����������Channel
			// ����channel������Nio��Channel����(NioServerSocketChannel)
			.channel(NioServerSocketChannel.class)
			// ��Դ��߳������һЩ����
			.childHandler(new HelloServerInitializer());
		ChannelFuture channelFuture;
		try {
			// ��������������, ��������Ϊ9998�˿�, ������ʽΪͬ��, bind����û�����ǰ, �̻߳�����
//		5. ���������͹رշ�����
			channelFuture = serverBootstrap.bind(9998).sync();
			// �����رշ�����, ��ʽҲΪͬ��
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bossGrp.shutdownGracefully();
			workerGrp.shutdownGracefully();
		}
	}
}
