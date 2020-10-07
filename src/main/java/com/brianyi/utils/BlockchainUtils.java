package com.brianyi.utils;

import com.alibaba.fastjson.JSON;
import com.brianyi.domain.BlockResponse;
import com.brianyi.domain.ResponseBlockData;
import com.brianyi.domain.Result;
import com.brianyi.domain.ToBlockData;
import okhttp3.*;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

/**
 * TODO
 *
 * @author ahao 2020-10-05
 */
public class BlockchainUtils {
    //指定媒体类型
    public static final MediaType MediaTypeJSON = MediaType
            .parse("application/json; charset=utf-8");
    /***.
     *@param dataId:
     * @param jsonData:
     *@author ahao
     *@date 2020-10-06 8:36
     *@return {@link Map< String, Object>}
     *数据上链
     */
    public static Map<String,Object> insertIntoChain(String dataId,String jsonData) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建json 请求体
        Map<String,String> body = new Hashtable<>();
        body.put("Authorization","eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJzdWIiOiI3MDk2MDEzMjA2MDAwMDE2MDEyOTIxMjAiLCJpYXQiOjE2MDE4Nzg2MjcsImV4cCI6MTYwMjQ4MzQyN30.GG05mpfdj1WtiyXbtytKBj5Ix-GISAal2doXr_Ro1y5nKgbH9A34sUNvKs3eh6ob6gSZt6cs4ghqDsTy5NYWxg");
        body.put("projectKey", "705200609700001585129307");
        body.put("func", "insert");
        body.put("chaincodeName","witnesscc");
        body.put("dataId",dataId);
        body.put("data",jsonData);
        body.put("dataType","json");
        body.put("version","1.0");
        //转换json串
        String requestBody = JSON.toJSONString(body);
        //设置请求体
        RequestBody requestBody1 = RequestBody.create(MediaTypeJSON, requestBody);
        //设置请求
        Request request = new Request.Builder()
                .url("https://www.ubaas.net/api/xbaasstudio/publicCommonContractInvoke")
                .post(requestBody1)
                .header("Connection", "Keep-Alive")
                .header("TOKEN", "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJzdWIiOiI3MDk2MDEzMjA2MDAwMDE2MDEyOTIxMjAiLCJpYXQiOjE2MDE4Nzg2MjcsImV4cCI6MTYwMjQ4MzQyN30.GG05mpfdj1WtiyXbtytKBj5Ix-GISAal2doXr_Ro1y5nKgbH9A34sUNvKs3eh6ob6gSZt6cs4ghqDsTy5NYWxg")
                .header("Content-type", "application/json;charset=UTF-8")
                .header("User-Agent","Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Mobile Safari/537.36")
                .build();
        Response response = null;
        Map result = new Hashtable();
        try {
            //执行请求
            response = okHttpClient.newCall(request).execute();
            result = JSON.parseObject(response.body().string(), Map.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据区块号查询区块内容
     *
     *
     * 当区块不存在时会报 空异常 原因:没有对两种返回情况做统一的处理 这只考虑请求成功的情况
     */
    public static Result searchBlockData(int blockNum) {
        Result result = new Result();
        BlockResponse responseData = new BlockResponse();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://www.ubaas.net/api/xbaasstudio/getPublicSearchDetail?projectKey=705200609700001585129307&value="+blockNum)
                .get()
                .header("Connection", "Keep-Alive")
                .header("TOKEN", "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJzdWIiOiI3MDk2MDEzMjA2MDAwMDE2MDEyOTIxMjAiLCJpYXQiOjE2MDE4Nzg2MjcsImV4cCI6MTYwMjQ4MzQyN30.GG05mpfdj1WtiyXbtytKBj5Ix-GISAal2doXr_Ro1y5nKgbH9A34sUNvKs3eh6ob6gSZt6cs4ghqDsTy5NYWxg")
                .header("Content-type", "application/json;charset=UTF-8")
                .header("User-Agent","Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Mobile Safari/537.36")
                .build();

        Response response = null;
        try {
            //执行请求
            response = okHttpClient.newCall(request).execute();
            responseData = JSON.parseObject(response.body().string(), BlockResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回数据判定 设置返回结果
        if(responseData.getCode() == 200){
            result.setCode(Result.SUCCESS);
            result.setMessage("查询成功");
            //获取集合第一个
            result.setObj(responseData.getData().get(0));
        }else {
            result.setCode(Result.FAILS);
            result.setMessage(responseData.getMsg());
        }
        return result;
    }

//    /**
//     *获取当前块 可以实现 但是并发怎么办
//     */
//    @Test
//    public void currBlockInfo() throws IOException {
//
//
//    }
    @Test
    public void insertTest() throws IOException {
        Result result = new Result();
        result.setCode(Result.FAILS);
        result.setMessage("以下为译文：\n" +
                "\n" +
                "数据库是应用及计算机的核心元素，负责存储运行软件应用所需的一切重要数据。为了保障应用正常运行，总有一个甚至多个数据库在默默运作。我们可以把数据库视为信息仓库，以结构化的方式存储了大量的相关信息，并合理分类，方便搜索及使用。\n" +
                "\n" +
                "因此，数据库设计成为软件开发中的重要一环，对于开发者来说，设计一个高效的数据库至关重要。那么，为什么数据库设计很重要，“好”的标准又是什么？本文将做以介绍。\n" +
                "\n" +
                "\n" +
                "\n" +
                "为什么数据库设计很重要？\n" +
                "\n" +
                "用我们订购衬衫的在线商城网站举例。如今，从网站上订购衬衫的过程似乎很简单，但涉及了很多信息交换。例如：第一次浏览网页时，系统会展示给你一系列信息，包含产品分类，所有可用商品、相应价格、可用尺码、颜色以及其他相关信息。网站会从存储数据的数据库中检索，当用户选中所需商品并继续操作购买时，网站会询问个人详细信息、送货地址、付款详细信息，并确认订单。目前在此过程中产生的新数据，如用户详细信息、订单详细信息、购买及付款都会在同一个数据库中添加及更新。\n" +
                "\n" +
                "数据库设计很重要，因为对构建可伸缩且能够在高工作负载下运行的软件应用来说，它是至关重要的。设计数据库首先来说，选择数据库软件很关键。目前可用于构建应用的数据库软件有数百种可供选择，我们可以从一些最好的免费数据库软件中选择，之后便是设计符合要求的数据库了。下面列出了10个最优的数据库设计实践。\n" +
                "\n" +
                "\n" +
                "\n" +
                "数据库设计的10个最佳实践\n" +
                "\n" +
                "将所有人的观点列入考量\n" +
                "\n" +
                "要设计好的数据库，必须考虑所有相关利益者的观点。在构建数据库之前，先去收集信息，了解他们对数据库的期望以及对数据库的操作熟练度。这样就能得出数据库应当采用的技术水平，以及是否要就数据库的功能来训练用户。\n" +
                "\n" +
                "选择符合需求的数据库类型\n" +
                "\n" +
                "数据库有多种类型，选择正确类型则是数据库设计的关键。我们可以将数据库以两种方式分类。一是基于数据库用以定义和操作数据的查询语言。使用SQL的数据库是结构化数据最常用的类型。然而，由于NoSQL数据库的可伸缩性、灵活性和速度更优，它们更适合机器学习、网络分析以及物联网（IoT）使用。\n" +
                "\n" +
                "第二种分类方式则是基于数据模型。这样分类会有四种类型：关系数据库、分层数据库、网络数据库以及面向对象的数据库。\n" +
                "\n" +
                "研究数据库的不同类型，并针对应用需求作出选择，这是必要的初始步骤。\n" +
                "\n" +
                "以一致的方式来定义与标记表和列\n" +
                "\n" +
                "在定义数据项以及标记表与列时，遵从一致性原则非常重要，可以帮助我们更好地理解数据。命名表与列的最佳实践之一便是使用简单的名字来定义其包含的数据。比如：只需将包含用户名称的列标记为“CustomerName”（“用户名”）即可。\n" +
                "\n" +
                "应当避免使用复数名（如CustomerNames）、缩写（如CN），并且名称中不得使用空格（如Customer Name）。如果坚持遵守这些规则，则将来的用户在使用数据库时会更容易。\n" +
                "\n" +
                "规范化是关键\n" +
                "\n" +
                "数据库的规范化指的是将数据库中的所有信息组织起来，避免数据重复和冗余。简单来说，规范化是将数据打散分配到多个较小的相关表中，而不是统统存在一个大表里。\n" +
                "\n" +
                "将数据规范化是很好的数据库设计实践，有助于提高效率。但是请确保不要过度规范化，否则将会把数据分散到太多的小表中，反而造成混乱。\n" +
                "\n" +
                "数据库设计的文档化很重要\n" +
                "\n" +
                "事实上，文档化并非人见人爱，因为实在太烦了。但要记得，文档化对于良好的数据库设计至关重要，可以追踪所有的小细节。数据库设计应当附有指示说明、ER图、存储过程及所有其他相关的信息。文档还应当为编程者和终端用户提供足够信息量，确保他们能够理解并使用。\n" +
                "\n" +
                "隐私是首要考量\n" +
                "\n" +
                "很多时候，存储在数据库中的信息是加密信息，隐私就成了一个值得关注的问题。为了获得最大程度的安全性，我们应当对密码加密，使用身份验证来限制数据库的访问，并使用另一个服务器来存放数据库，而不是应用所在的服务器。这将确保你的数据不受攻击和隐私侵犯的威胁。\n" +
                "\n" +
                "考虑长期需求\n" +
                "\n" +
                "优秀的数据库设计应当具备可伸缩性，即：在使用量增加的情况下，仍然能承受较高的工作负载，并保障应用的运行。对工作量可能会有所改变的企业来说，在设计数据库时牢记此项至关重要。例如：如果一个电子商务网站预计当销售量增长时，访客会在一个月内急速增加，那么在设计数据库时应当将这一点列入考量，以便数据库可以响应迅速增长的访客，并保持在高工作负载的情况下运作。\n" +
                "\n" +
                "代码以及使用预存程序\n" +
                "\n" +
                "数据库设计中的常见错误之一，就是不使用预存程序。预存程序指的是，在操作数据时手边随时可用的提前预存代码串。例如，如果有个SQL查询是常用的操作指令，则将它提前写入预存程序，而不是在用的时候重新来写，就会让工作简单起来。一旦有了预存程序，就可以在需要时一步执行并载入这个SQL查询了。\n" +
                "\n" +
                "编写大量预存程序是个吃力的工作，但如果花时间完成并用文档记录下来的话，对终端用户来说，使用数据库就更加轻松了。\n" +
                "\n" +
                "在数据库建模和设计上投入时间\n" +
                "\n" +
                "优秀数据库设计的专业技巧之一是在数据库建模和设计上投入时间和精力。开发者常见的错误就是忽略这一步以节省时间，将重点放在软件开发更重要的其他方面上。但是，数据库设计对于保障应用的功能来说非常关键。如果一味想节省时间，不仔细思考设计的话，未来就会需要花费更多时间来维护数据库，甚至重新设计。\n" +
                "\n" +
                "测试设计\n" +
                "\n" +
                "测试也是数据库设计很关键的步骤，而且经常会被疏忽对待，甚至完全跳过，只为了赶DDL。我们应当在发布项目前，花些时间来彻底测试数据库设计，以确保其满足了所有计划中的需求，且正常运行。\n" +
                "\n" +
                "\n" +
                "\n" +
                "结语\n" +
                "\n" +
                "对于以数据为中心的项目来说，数据库设计极为重要，开发过程中我们也应当慎重对待。文中提到的数据库设计实践，如针对长期需求进行适当的计划，选择正确的数据库类型，使用一致性的名称与标签，数据规范化，数据库设计文档化，并提前测试，这些都是我们在设计优秀数据库时需要考虑的关键问题。\n" +
                "\n" +
                "原文：https://medium.com/quick-code/10-best-database-design-practices-1f10f3441730\n" +
                "\n" +
                "本文为 CSDN 翻译，转载请注明来源出处。\n" +
                "\n" +
                "\n" +
                "\n" +
                "「AI大师课」是CSDN发起的“百万人学AI”倡议下的重要组成部分，4月份AI大师课以线上技术峰会的形式推出，来自微软、硅谷TigerGraph、北邮等产学界大咖就图计算+机器学习，语音技术、新基建+AI、AI+医疗等主题展开分享，扫描下方二维码免费报名，限时再送299元「2020AI开发者万人大会」门票一张。\n" +
                "\n" +
                "\n" +
                "\n" +
                "推荐阅读：Spark3.0发布了，代码拉过来，打个包，跑起来！| 附源码编译\n" +
                "跟面试官侃半小时MySQL事务隔离性，从基本概念深入到实现\n" +
                "技术大佬的肺腑之言：“不要为了 AI 而 AI”！ | 刷新 CTO\n" +
                "无代码时代来临，程序员如何保住饭碗？\n" +
                "154 万 AI 开发者用数据告诉你，中国 AI 如何才能弯道超车？| 中国 AI 应用开发者报告\n" +
                "业内最大的“空气币”——以太坊？？？\n" +
                "真香，朕在看了");
        UUID uuid = UUID.randomUUID();
        String articleId = uuid.toString().replace("-", "");
        ToBlockData toBlockData = new ToBlockData();
        toBlockData.setArticleId(articleId);
        toBlockData.setTitle("title");
        toBlockData.setText("content");
        System.out.println(JSON.toJSONString(result));
        Map<String, Object> map = insertIntoChain(articleId, JSON.toJSONString(toBlockData));
        map.forEach((key,v)->{
            System.out.println(key+":"+v);
        });
    }
//    @Test
//    public void getBlockTest() {
//        System.out.println(((ResponseBlockData)searchBlockData(30).getObj()).getBlockHash());
//    }


}
