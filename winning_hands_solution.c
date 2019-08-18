//intended solution
#include <iostream>
#include <cstdio>
#include <string>
#include <sstream> 
#include <vector>
#include <set>
#include <map>
#include <queue>
#include <stack>
#include <cmath>
#include <algorithm>
#include <cstring>
#include <ctime>
#include <cassert>
using namespace std;
#define pb push_back
#define mp make_pair
#define pii pair<int,int>
#define vi vector<int>
#define vpii vector<pii>
#define SZ(x) ((int)(x.size()))
#define fi first
#define se second
#define FOR(i,n) for(int (i)=0;(i)<(n);++(i))
#define FORI(i,n) for(int (i)=1;(i)<=(n);++(i))
#define IN(x,y) ((y).find((x))!=(y).end())
#define ALL(t) t.begin(),t.end()
#define FOREACH(i,t) for (typeof(t.begin()) i=t.begin(); i!=t.end(); i++)
#define REP(i,a,b) for(int (i)=(a);(i)<=(b);++i)
#define REPD(i,a,b) for(int (i)=(a); (i)>=(b);--i)
#define REMAX(a,b) (a)=max((a),(b));
#define REMIN(a,b) (a)=min((a),(b));
#define DBG cerr << "debug here" << endl;
#define DBGV(vari) cerr << #vari<< " = "<< (vari) <<endl;

typedef long long ll;
const int INF = 1e9;

const int N = 30;
const int M = 1e6;
const int V = 1e7;

int a[N];
int dp[2][M];

int main()
{
    ios_base::sync_with_stdio(0);
    int t=1;
    while (t--) {
        int n, m, x;
        cin >> n >> m >> x;
        assert(n >= 1 && n <= N);
        assert(m >= 1 && m <= M);
        assert(x >= 0 && x <= m-1);
        FOR(i, n) {
            cin >> a[i];
            assert(a[i] >= 1 && a[i] <= V);
        }
        int cur = 0;
        REP(r, 0, m-1) dp[1-cur][r] = 0;
        FOR(i, n) {
            REP(r, 0, m-1) {
                dp[cur][r] = dp[1-cur][r];
            }
            REP(r, 0, m-1) {
                dp[cur][((ll)r*a[i])%m] += dp[1-cur][r];
            }
            dp[cur][a[i]%m]++;
            cur = 1-cur;
        }
        cout << dp[1-cur][x] << endl;
    }

    return 0;
}