/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.202.129
 Source Server Type    : MySQL
 Source Server Version : 50744
 Source Host           : 192.168.202.129:3306
 Source Schema         : shop-seckill

 Target Server Type    : MySQL
 Target Server Version : 50744
 File Encoding         : 65001

*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_order_info
-- ----------------------------
DROP TABLE IF EXISTS `t_order_info`;
CREATE TABLE `t_order_info`  (
  `order_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delivery_addr_id` bigint(20) NULL DEFAULT NULL,
  `product_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_price` decimal(10, 2) NULL DEFAULT NULL,
  `seckill_price` decimal(10, 2) NULL DEFAULT NULL,
  `status` tinyint(4) NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `pay_date` datetime(0) NULL DEFAULT NULL,
  `seckill_date` date NULL DEFAULT NULL,
  `seckill_time` int(11) NULL DEFAULT NULL,
  `integral` decimal(10, 0) NULL DEFAULT NULL,
  `seckill_id` bigint(20) NULL DEFAULT NULL,
  `pay_type` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`order_no`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`, `seckill_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_pay_log
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_log`;
CREATE TABLE `t_pay_log`  (
  `order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pay_time` datetime(0) NULL DEFAULT NULL,
  `total_amount` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`order_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_refund_log
-- ----------------------------
DROP TABLE IF EXISTS `t_refund_log`;
CREATE TABLE `t_refund_log`  (
  `order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `refund_time` datetime(0) NULL DEFAULT NULL,
  `refund_amount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `refund_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`order_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_seckill_product
-- ----------------------------
DROP TABLE IF EXISTS `t_seckill_product`;
CREATE TABLE `t_seckill_product`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_img` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `seckill_price` decimal(10, 2) NULL DEFAULT NULL,
  `product_detail` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `product_price` decimal(10, 2) NULL DEFAULT NULL,
  `integral` decimal(10, 0) NULL DEFAULT NULL,
  `stock_count` int(255) NULL DEFAULT NULL,
  `start_date` date NULL DEFAULT NULL,
  `time` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_seckill_product
-- ----------------------------
INSERT INTO `t_seckill_product` VALUES (2, '华为nova7pro 8G+128G全网通', '麒麟985芯片，卖一台少一台，华为直供货源，全新原封正品【赠】原装碎屏险+2年保修+运费险+液态硅胶壳+贴膜', '/img/p1.jpg', 3699.00, '华为nova7pro 5G手机 7号色 8G+128G全网通', 4399.00, 36990, 10, '2023-11-02', 10);
INSERT INTO `t_seckill_product` VALUES (3, 'VAIO 11代新品笔记本', ' VAIO FH14 侍14 11代酷睿 14英寸 1.4Kg 4G独显 高性能轻薄笔记本电脑(i5 16G 512G SSD GTX1650 FHD)铂金银', '/img/p2.jpg', 2999.00, ' VAIO FH14 侍14 11代酷睿 14英寸 1.4Kg 4G独显 高性能轻薄笔记本电脑(i5 16G 512G SSD GTX1650 FHD)铂金银', 4999.00, 29990, 10, '2023-11-02', 10);
INSERT INTO `t_seckill_product` VALUES (4, '独牙（DUYA）可充电无线蓝牙鼠标', '三模办公静音锂电池鼠标 2.4G蓝牙5.0笔记本IPAD电脑平板通用 M203太空银', '/img/p3.jpg', 9.00, '三模办公静音锂电池鼠标 2.4G蓝牙5.0笔记本IPAD电脑平板通用 M203太空银', 49.00, 90, 10, '2023-11-02', 10);
INSERT INTO `t_seckill_product` VALUES (5, '玉翊 新疆和田玉', '观音吊坠男羊脂白玉佛玉坠女本命佛挂件 羊脂玉佛小号【25*26*6】', '/img/p4.jpg', 899.00, '观音吊坠男羊脂白玉佛玉坠女本命佛挂件 羊脂玉佛小号【25*26*6】', 1399.00, 8990, 10, '2023-11-02', 10);
INSERT INTO `t_seckill_product` VALUES (6, '雅鹿品牌 加绒加厚  连帽夹克男', '雅鹿(YALU)【加绒可选】外套男 秋冬新款男装连帽夹克外套男士衣服男装外套12.3 601浅蓝米白(常规款) L码', '/img/p5.jpg', 59.00, '雅鹿(YALU)【加绒可选】外套男 秋冬新款男装连帽夹克外套男士衣服男装外套12.3 601浅蓝米白(常规款) L码', 199.00, 590, 10, '2023-11-02', 10);
INSERT INTO `t_seckill_product` VALUES (7, '创维10公斤大容量变频滚筒', '创维(SKYWORTH) 10公斤 滚筒洗衣机全自动 家用一级变频 除菌除螨 桶自洁 15分快洗 XQG100-B15LB', '/img/p6.jpg', 1699.00, '创维(SKYWORTH) 10公斤 滚筒洗衣机全自动 家用一级变频 除菌除螨 桶自洁 15分快洗 XQG100-B15LB', 2399.00, 16990, 10, '2023-11-02', 10);
INSERT INTO `t_seckill_product` VALUES (8, '夏普70英寸4K+HDR10液晶电视', '夏普（SHARP）70A5RD 70英寸日本原装面板4K超清网络智能液晶平板电视', '/img/p7.jpg', 3999.00, '夏普（SHARP）70A5RD 70英寸日本原装面板4K超清网络智能液晶平板电视', 6399.00, 3999, 10, '2023-11-02', 10);
INSERT INTO `t_seckill_product` VALUES (9, 'Redmi K30 5G 6GB+128GB紫玉幻境', 'Redmi K30 5G双模 120Hz流速屏 骁龙765G 前置挖孔双摄 索尼6400万后置四摄 30W快充 6GB+128GB 紫玉幻境 游戏智能手机 小米 红米', '/img/p8.jpg', 1099.00, 'Redmi K30 5G双模 120Hz流速屏 骁龙765G 前置挖孔双摄 索尼6400万后置四摄 30W快充 6GB+128GB 紫玉幻境 游戏智能手机 小米 红米', 1699.00, 10990, 10, '2023-11-02', 10);
INSERT INTO `t_seckill_product` VALUES (10, 'Apple Watch S5 GPS款 40毫米', 'Apple Watch Series 5智能手表（GPS款 40毫米深空灰色铝金属表壳 黑色运动型表带 MWV82CH/A)', '/img/p9.jpg', 2399.00, '【事事拿手，轻松入手！】【一站式以旧watch换新享额外200-500补贴优惠！】【新品再享30天试用！】库存紧张，马上抢购！', 3199.00, 23990, 10, '2023-11-02', 12);
INSERT INTO `t_seckill_product` VALUES (11, '荣耀手表GS Pro 碳石黑', '荣耀手表GS Pro 碳石黑 25天续航 华为麒麟A1芯 103种运动模式 军规 智能语音蓝牙通话 50米防水 心率血氧GPS', '/img/p10.jpg', 799.00, '荣耀手表GS Pro 碳石黑 25天续航 华为麒麟A1芯 103种运动模式 军规 智能语音蓝牙通话 50米防水 心率血氧GPS', 1599.00, 7990, 10, '2023-11-02', 12);
INSERT INTO `t_seckill_product` VALUES (12, '仓鼠太空舱暖手宝充电宝二合一', '中意礼暖手宝充电暖宝宝电暖热水袋圣诞节礼物生日礼物送女友闺蜜女朋友情人节礼物送老婆结婚纪念日圣诞礼物 太空仓鼠暖手宝', '/img/p11.jpg', 199.00, '【冬日优选，超温暖】超萌仓鼠形象，女生一见倾心，5秒快速升温，四档恒温，立体公仔，持续暖手3-6小时', 299.00, 1990, 10, '2023-11-02', 12);
INSERT INTO `t_seckill_product` VALUES (13, '【价同12.12】美的60升电热爆款', '美的（Midea）60升电热水器2100W变频速热健康洗 智能APP控制 一级节能自动关机F6021-JA1(HEY)', '/img/p12.jpg', 949.00, '【12月厨卫开门红】冰点价949！限时秒杀！ 【2100W变频速热！健康沐浴！一级节能】 【手机APP控制！8年包修】鸿蒙新品上市', 1999.00, 9490, 10, '2023-11-02', 12);
INSERT INTO `t_seckill_product` VALUES (14, '麦瑞克航空专利减震家用跑步机', '【航空专利减震】【送上楼包安装】麦瑞克Merach跑步机家用静音折叠走步机运动健身器材 【推荐款】10.1吋彩屏多功能/航空减震/带按摩机', '/img/p13.jpg', 2499.00, '【12.4日开门红】抢伴价、抢八折，爆款前一小时限时直降！更有华为P40pro+、爆款椭圆机等抽不停！白条免息运费险等福利领取！', 3999.00, 24990, 10, '2023-11-02', 12);
INSERT INTO `t_seckill_product` VALUES (15, '华为畅享20 5G手机【大屏幕/大电池】 绮境森林 6+128G 全网通', '华为畅享20 5G手机【大屏幕/大电池】 绮境森林 6+128G 全网通', '/img/p14.jpg', 1199.00, '咨询客服享优惠】AI三摄闪拍，5000mAh电池【另有畅享20plus点此】', 2299.00, 11990, 10, '2023-11-02', 12);
INSERT INTO `t_seckill_product` VALUES (16, '三星Galaxy Note20 Ultra 5G手机游戏手机 12GB+256GB 迷雾金', '三星Galaxy Note20 Ultra 5G(SM-N9860)S Pen&三星笔记 120Hz自适应屏幕 5G手机游戏手机 12GB+256GB 迷雾金', '/img/p15.jpg', 5999.00, '白条12期免息！下单赠价值1199元BudsLive耳机！全场至高优惠4000元！', 9199.00, 59990, 10, '2023-11-02', 12);
INSERT INTO `t_seckill_product` VALUES (17, '洁滔（GILLTAO）德国三档强力增压淋浴花洒喷头', '洁滔（GILLTAO）德国三档强力增压淋浴花洒喷头手持沐浴洗澡淋雨大出水可拆洗万向旋转莲蓬头套装 三挡增压止水瓷白款', '/img/p16.jpg', 9.00, '12.02号下午2点掌上秒杀，瓷白单喷头仅需19元，两件再享9折，上期秒杀2小时3500单抢光，欲购从速。三挡增压，一键止水，可拆洗，头部万向旋转，不增压包退。', 49.00, 90, 10, '2023-11-02', 12);
INSERT INTO `t_seckill_product` VALUES (18, 'OPPO K7x 新品双模5G手机 90Hz电竞屏 智能拍照游戏 ', 'OPPO K7x 新品双模5G手机 90Hz电竞屏 智能拍照游戏 长续航手机oppok7x 黑镜 6+128G【现货速发】 全网通5G 闪充套装', '/img/p17.jpg', 999.00, '【现货速发，到手1499起】购机送蓝牙佴机+1年屏碎险+2年质保+晒单20+会员积分~5G硬核新品K7x赠蓝牙佴机', 1599.00, 9990, 10, '2023-11-02', 14);
INSERT INTO `t_seckill_product` VALUES (19, '松下5款吸嘴家用大吸力吸尘器', '松下（panasonic）家用吸尘器大吸力小型地毯床上办公室强力除尘吸尘器宠物家庭适用 MC-WLC87字母负离子吸嘴', '/img/p18.jpg', 659.00, '【锁定12.3日10点】限时好价659！错过12.12都没有！ 【团购咨询客服】可领取惊喜出厂价哦！ 【全国联保，售后无忧】180以换代修！立即加购', 1499.00, 6590, 10, '2023-11-02', 14);
INSERT INTO `t_seckill_product` VALUES (20, '阿玛尼商务钢带男士时尚石英表', '【品牌授权】Emporio Armani 阿玛尼手表 欧美表 商务男士腕表 皮带钢带男表石英表 AR1819', '/img/p19.jpg', 1999.00, '【大牌秒杀】爆款男士钢带表AR819到手价1300更多优惠请咨询再线客服', 2990.00, 19990, 10, '2023-11-02', 14);
INSERT INTO `t_seckill_product` VALUES (21, '联想拯救者12GB+256GB  炽焰战甲', '联想拯救者电竞手机Pro 12GB+256GB 骁龙865 Plus 双液冷散热 144Hz电竞屏 双模5G游戏手机 炽焰战甲', '/img/p20.jpg', 3399.00, '【专享2500元VIP特权】高通骁龙865plus游戏高手的选择，双模5G，144Hz电竞屏，双液冷散热，晒单得50元E卡！至尊透明版', 4199.00, 33990, 10, '2023-11-02', 14);

-- ----------------------------
-- Table structure for tcc_fence_log
-- ----------------------------
DROP TABLE IF EXISTS `tcc_fence_log`;
CREATE TABLE `tcc_fence_log`  (
  `xid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'global id',
  `branch_id` bigint(20) NOT NULL COMMENT 'branch id',
  `action_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'action name',
  `status` tinyint(4) NOT NULL COMMENT 'status(tried:1;committed:2;rollbacked:3;suspended:4)',
  `gmt_create` datetime(3) NOT NULL COMMENT 'create time',
  `gmt_modified` datetime(3) NOT NULL COMMENT 'update time',
  PRIMARY KEY (`xid`, `branch_id`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime(0) NOT NULL,
  `log_modified` datetime(0) NOT NULL,
  `ext` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
