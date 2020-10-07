package com.brianyi.utils;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.*;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

public class Similarity {
    Map<String, int[]> vectorMap = new HashMap<String, int[]>();

    int[] tempArray = null;

    public Similarity(String content1, String content2){
        List<String> words1 = segStr(content1);
        List<String> words2 = segStr(content2);
        for (int i = 0; i < words1.size(); i++) {
            if (vectorMap.containsKey(words1.get(i))) {
                vectorMap.get(words1.get(i))[0]++;
            } else {
                tempArray = new int[2];
                tempArray[0] = 1;
                tempArray[1] = 0;
                vectorMap.put(words1.get(i), tempArray);
            }
        }

        for (int i = 0; i < words2.size(); i++) {
            if (vectorMap.containsKey(words2.get(i))) {
                vectorMap.get(words2.get(i))[1]++;
            } else {
                tempArray = new int[2];
                tempArray[0] = 0;
                tempArray[1] = 1;
                vectorMap.put(words2.get(i), tempArray);
            }
        }

    }

    // 求余弦相似度
    public double sim() {
        double result = 0;
        result = pointMulti(vectorMap) / sqrtMulti(vectorMap);
        return result;
    }

    private double sqrtMulti(Map<String, int[]> paramMap) {
        double result = 0;
        result = squares(paramMap);
        result = Math.sqrt(result);
        return result;
    }

    // 求平方和
    private double squares(Map<String, int[]> paramMap) {
        double result1 = 0;
        double result2 = 0;
        Set<String> keySet = paramMap.keySet();
        for (String key : keySet) {
            int temp[] = paramMap.get(key);
            result1 += (temp[0] * temp[0]);
            result2 += (temp[1] * temp[1]);
        }
        return result1 * result2;
    }
    //分词
    public static List<String> segStr(String content){
        // 分词
        Reader input = new StringReader(content);
        // 智能分词关闭（对分词的精度影响很大）
        IKSegmenter iks = new IKSegmenter(input, true);
        Lexeme lexeme = null;
        List<String> list = new ArrayList<String>();

        try {
            while ((lexeme = iks.next()) != null) {
                list.add(lexeme.getLexemeText());
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 点乘法
    private double pointMulti(Map<String, int[]> paramMap) {
        double result = 0;
        for (String key :paramMap.keySet()) {
            int temp[] = paramMap.get(key);
            result += (temp[0] * temp[1]);
        }
        return result;
    }


    public static void main(String[] args) {
       /* */
//        String s1 = "苹果公司是美国的一家高科技公司";
//        String s2 = "小米公司是中国的一家高科技公司";
       /* String s1 = "Cascade, Correlation, architecture, supervised, learning, algorithm, artificial, neural, networks, Instead, adjusting, weights, network, fixed, topology, Cascade, Correlation, begins, minimal, network, automatically, trains, adds, hidden, units, creating, multi, layer, structure, Once, hidden, unit, added, network, input, side, weights, frozen, This, unit, permanent, feature, detector, network, producing, outputs, creating, complex, feature, detectors, The, Cascade, Correlation, architecture, advantages, existing, algorithms, learns, quickly, network, determines, size, topology, retains, structures, built, training, set, requires, back, propagation, error, signals, connections, network";
        String s2 = "This, paper, considers, firm, issue, common, stock, raise, cash, undertake, valuable, investment, opportunity, Management, assumed, firm, potential, investors, Investors, interpret, firm, actions, rationally, An, equilibrium, model, issue, invest, decision, developed, assumptions, The, model, shows, firms, refuse, issue, stock, pass, valuable, investment, opportunities, The, model, suggests, explanations, aspects, corporate, financing, behavior, including, tendency, rely, internal, sources, funds, prefer, debt, equity, external, financing, required, Extensions, applications, model, discussed";
      */
        String s3 = "[1, TCP, IP, 协议, 群, 含义, 字面, 意义, 讲, 有人, TCP, IP, 指, TCP, IP, 两种, 协议, 确实, 就是指, 这两种, 协议, 很多, 利用, IP, 进行通信, 时所/nr, 用到, 协议, 群, 统称, 具体来说, IP, ICMP, TCP, UDP, TELENT, FTP, HTTP, TCP, IP, 协议, TCP, IP, 词, 泛指, 协议, 也成, TCP, IP, 网际协议, 族, 2, Http, Http, 中文, 超文本, 传输, 协议, 客户端, 服务端, 一系列, 运作, 流程, 2.1, Http, 协议, IP, TCP, NDS, 稍微, 看一下, Http, Https, 区别, 3, TCP, TCP, TCP, IP, 协议, 族, 传输层, 一种, 3.1, TCP, 目的, IP, 数据报, 可靠性, 传输, 很多, 事情, 数据, 破坏, 丢包, 重复, 分片, 顺序, 混乱, 解决, 也就, 谈起, 可靠, 传输, TCP, 校验, 序列号, 确认, 重发, 控制, 连接, 窗口, 控制, 机制, 可靠性, 传输, 3.2, TCP, 确保, 数据, 到达, 目标, 确保, 无误, 数据, 送达, 目标, 处, TCP, 协议, 采用, 三次, 握手, 策略, TCP, 协议, 数据包, 送, TCP, 传送, 后的/nr, 置之不理, 一定会, 对方, 确认, 成功, 送达, 握手, 过程, TCP, 标志, flag, SYN, synchronize, ACK, acknowledgement, 发送, 端, 发送, 带, SYN, 标志, 数据, 包给, 对方, 接受端, 收到, 回传, 带有, SYN, ACK, 标志, 数据, 包, 以示, 传达, 确认, 信息, 发送, 端, 再回, 传给, 带, ACK, 标示, 数据, 包, 代表, 握手, 结束, 若在, 握手, 过程, 阶段, 莫名, 中断, TCP, 协议, 再次, 顺序, 发送, 数据包, 三次, 握手, 过程, 理解, 确认, 发送, 端, 接收端/n, 两端/m, 接收, 发送, 能力/n, 形如, 电话, 通话]";
        String s4 = "[基本概念, 1, TCP, 连接, 手机, 联网, 功能, 手机, 底层, TCP, IP, 协议, 使, 手机, 终端, 无线, 网络, 建立, TCP, 连接, TCP, 协议, 上层, 网络, 提供, 接口, 使, 上层, 网络, 数据, 传输, 建立在, 无差别, 网络, 之上, 建立, TCP, 连接, 三次, 握手, 第一, 握手, 客户端, 发送, syn, 包, syn, 服务器, SYN, SEND, 状态, 等待, 服务器, 确认, 握手, 服务器, 收到, syn, 包, 确认, 客户, SYN, ack, 1, 发送, SYN, 包, syn, SYN, ACK, 包, 服务器, SYN, RECV, 状态, 第三, 握手, 客户端, 收到, 服务器, SYN, ACK, 包, 服务器发送/n, 确认, 包, ACK/w, ack, 1, 包, 发送, 完毕, 客户端, 器, ESTABLISHED, 状态, 三次, 握手, 握手, 过程, 传送, 包里, 包含, 数据, 三次, 握手, 完毕, 客户端, 服务器, 才, 正式, 传送数据, 理想, 状态, TCP, 连接, 建立, 通信, 中的, 任何一方, 主动, 关闭, 接, TCP, 连接, 将被, 断开连接/l, 服务器/n, 客户端/n, 均/d, 主动/b, 发起, 断开, TCP/w, 连接, 请求, 断开, 过程, 四次, 握手, 过程, 就不, 细, 写, 服务器, 客户端, 交互, 最终, 断开, 2, HTTP, 连接, HTTP, 协议, 超文本, 传送, 协议, Hypertext, Transfer, Protocol, Web, 联网, 基础, 手机, 联网, 常用, 协议, HTTP, 协议, 建立, TCP, 协议, 之上, 一种, HTTP, 连接, 客户端, 发送, 每次, 请求, 服务器, 回送, 响应, 请求, 结束, 主动, 释放, 连接, 建立, 连接, 关闭, 连接, 过程, 称为, 连接, 1, HTTP, 1.0中, 客户端, 每次, 请求, 建立, 单独, 连接, 完, 本次, 请求, 自动, 释放, 连接, 2, HTTP, 1.1中, 连接, 多个, 请求, 多个, 请求, 重叠, 等待, 请求, 结束, 后再, 发送, 下一个, 请求, HTTP, 每次, 请求, 结束, 都会, 主动, 释放, 连接, HTTP, 连接, 一种, 短, 连接, 客户端程序, 在线, 状态, 服务器, 发起, 连接, 请求, 做法, 即时, 数据, 客户端, 每隔, 一段, 固定, 服务器发送, 连接, 请求, 服务器, 收到, 请求, 后对/nr, 客户端, 回复, 表, 明知道, 客, 户, 端, 在线, 服务器, 长时间, 收到, 客户端, 请求, 客户端, 下线, 客户端, 长时间, 收到, 服务器, 回复, 网络, 断开]";
        Similarity similarity = new Similarity(s3, s4);
        double sim_value = similarity.sim();
        System.out.println(sim_value);
    }

}