package 并查集;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 721. 账户合并 中等
 *
 * 给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
 * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。
 * 一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
 * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是 按字符 ASCII 顺序排列 的邮箱地址。账户本身可以以 任意顺序 返回。
 *
 * 示例 1：
 * 输入：accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * 输出：[["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 * 解释：
 * 第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。
 * 第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
 * 可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。
 *
 * 示例 2：
 * 输入：accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
 * 输出：[["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
 *
 *
 * 提示：
 * 1 <= accounts.length <= 1000
 * 2 <= accounts[i].length <= 10
 * 1 <= accounts[i][j].length <= 30
 * accounts[i][0] 由英文字母组成
 * accounts[i][j] (for j > 0) 是有效的邮箱地址
 */
public class AccountsMerge721 {
    public static void main(String[] args) {
//        List<List<String>> accounts = new ArrayList<>();
//        accounts.add(new ArrayList<>(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com")));
//        accounts.add(new ArrayList<>(Arrays.asList("John", "johnnybravo@mail.com")));
//        accounts.add(new ArrayList<>(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com")));
//        accounts.add(new ArrayList<>(Arrays.asList("Mary", "mary@mail.com")));

        List<List<String>> accounts = new ArrayList<>();
        accounts.add(new ArrayList<>(Arrays.asList("Alex", "Alex5@m.co", "Alex4@m.co", "Alex0@m.co")));
        accounts.add(new ArrayList<>(Arrays.asList("Ethan", "Ethan3@m.co", "Ethan3@m.co", "Ethan0@m.co")));
        accounts.add(new ArrayList<>(Arrays.asList("Kevin", "Kevin4@m.co", "Kevin2@m.co", "Kevin2@m.co")));
        accounts.add(new ArrayList<>(Arrays.asList("Gabe", "Gabe0@m.co", "Gabe3@m.co", "Gabe2@m.co")));
        accounts.add(new ArrayList<>(Arrays.asList("Gabe", "Gabe3@m.co", "Gabe4@m.co", "Gabe2@m.co")));
        System.out.println(new AccountsMerge721().accountsMerge_First(accounts));
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        return null;
    }








































































    public List<List<String>> accountsMerge_First(List<List<String>> accounts) {
        Map<String, List<Integer>> emailMapAccountIndex =  new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                List<Integer> accountIndex = emailMapAccountIndex.getOrDefault(email, new ArrayList<>());
                accountIndex.add(i);
                emailMapAccountIndex.put(email, accountIndex);
            }
        }
        DSU_First dsu = new DSU_First(accounts.size());

        // 先合并，顺便路径压缩，把find方法做到O(1)
        for (List<Integer> indexes: emailMapAccountIndex.values()) {
            for (int i = 0, j = i + 1; j < indexes.size(); i++, j++) {
                dsu.union(indexes.get(i), indexes.get(j));
            }
        }

        // 根据 find 方法查询 email 属于哪个 root
        Map<Integer, Set<String>> rootMapEmails = new HashMap<>();
        for (Map.Entry<String, List<Integer>> entry : emailMapAccountIndex.entrySet()) {
            String email = entry.getKey();
            int index = entry.getValue().get(0);
            int root = dsu.find(index);
            Set<String> merge = rootMapEmails.getOrDefault(root, new HashSet<>());
            merge.add(email);
            rootMapEmails.put(root, merge);
        }

        // 转换答案
        List<List<String>> ans = new ArrayList<>();
        for (Map.Entry<Integer, Set<String>> entry : rootMapEmails.entrySet()) {
            Integer rootIndex = entry.getKey();
            String user = accounts.get(rootIndex).get(0);
            Set<String> emails = entry.getValue();
            List<String> list = new ArrayList<>();
            list.add(user);
            list.addAll(emails.stream().sorted().collect(Collectors.toList()));
            ans.add(list);
        }
        return ans;

    }

    static class DSU_First {
        private final int[] parent;
        private final int[] size;

        public DSU_First(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        private int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            if (ra == rb) {
                return;
            }

            // 小树挂到大树下
            if (size[ra] < size[rb]) {
                parent[ra] = rb;
                size[rb] += size[ra];
            } else {
                parent[rb] = ra;
                size[ra] += size[rb];
            }
        }
    }
}
