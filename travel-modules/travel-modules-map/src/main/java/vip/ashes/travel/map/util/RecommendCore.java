package vip.ashes.travel.map.util;

import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import vip.ashes.travel.map.entity.UserRatePoi;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author loveliness
 */
public class RecommendCore {
    /**
     * pearson相关系数计算
     *
     * @param xs 别人对poi的评分集合
     * @param ys 自己对poi的评分集合
     */
    public static Double getRelate(List<Double> xs, List<Double> ys) {
        int n = xs.size();
        double ex = xs.stream().mapToDouble(x -> x).sum();
        double ey = ys.stream().mapToDouble(y -> y).sum();
        double Ex2 = xs.stream().mapToDouble(x -> Math.pow(x, 2)).sum();
        double Ey2 = ys.stream().mapToDouble(y -> Math.pow(y, 2)).sum();
        double Exy = IntStream.range(0, n).mapToDouble(i -> xs.get(i) * ys.get(i)).sum();
        double numerator = Exy - ex * ey / n;
        double denominator = Math.sqrt((Ex2 - Math.pow(ex, 2) / n) * (Ey2 - Math.pow(ey, 2) / n));
        if (denominator == 0) {
            return 0.0;
        }
        return numerator / denominator;
    }

    public List<String> recommend(String userId, List<UserRatePoi> list) {
        // 根据用户id分组
        Map<String, List<UserRatePoi>> userMap = list.stream().collect(Collectors.groupingBy(UserRatePoi::getUserId));

        //找到最近邻用户id
        // distances key 距离
        // distances val 用户id
        Map<Double, String> distances = computeNearestNeighbor(userId, userMap);
        // 相关系数为1的用户
        Iterator<String> iterator = distances.values().iterator();
        String nearest = "";

        while (iterator.hasNext()) {
            nearest = iterator.next();
        }

        //最近邻用户评价过列表
        List<String> neighborItemList = userMap.get(nearest).stream().map(UserRatePoi::getBaiduPoiUid).collect(Collectors.toList());
        //指定用户评价过列表
        List<String> userItemList = userMap.get(userId).stream().map(UserRatePoi::getBaiduPoiUid).collect(Collectors.toList());

        //找到最近邻评价过，但是该用户没评价过的，计算推荐，放入推荐列表
        List<String> recommendList = new ArrayList<>();
        for (String item : neighborItemList) {
            if (!userItemList.contains(item)) {
                recommendList.add(item);
            }
        }
        Collections.sort(recommendList);
        return recommendList;
    }

    /**
     * 在给定userId的情况下，计算其他用户和它的相关系数并排序
     *
     * @param userId 用户id
     * @return key距离 val用户id
     */
    private Map<Double, String> computeNearestNeighbor(String userId, Map<String, List<UserRatePoi>> userMap) {
        Map<Double, String> distances = new TreeMap<>();
        userMap.forEach((k, v) -> {
            // 当前列表不是自己
            if (!k.equals(userId)) {
                // 计算用户与其他人的距离/相似度
                // v 单用户用户集合
                // userMap.get(userId) 该用户集合
                double distance = pearsonDis(v, userMap.get(userId));
                distances.put(distance, k);
            }
        });
        return distances;
    }

    /**
     * 计算两个序列间的相关系数
     *
     * @param xList 别人对物品的评价
     * @param yList 自己对物品的评价
     * @return 相关系数
     */
    private double pearsonDis(List<UserRatePoi> xList, List<UserRatePoi> yList) {
        List<Double> xs = Lists.newArrayList();
        List<Double> ys = Lists.newArrayList();
        // 双层遍历
        for (UserRatePoi x : xList) {
            for (UserRatePoi y : yList) {
                // 自己和别人评价的物品相同
                if (x.getBaiduPoiUid().equals(y.getBaiduPoiUid())) {
                    xs.add(x.getRate());
                    ys.add(y.getRate());
                }
            }
        }
        return getRelate(xs, ys);
    }

}
