import java.util.*;
import java.math.BigInteger;
import java.util.StringTokenizer;
public class SH
{
    public int hashbits = 64;
    public BigInteger simHash( String doc)
    {	
        int[] v = new int[hashbits];
        StringTokenizer stringTokens = new StringTokenizer(doc);
	
        while (stringTokens.hasMoreTokens())
        {
            String temp = stringTokens.nextToken();
	    BigInteger t = hash(temp);
	    for (int i = 0; i < hashbits; i++)
            {
                BigInteger bitmask = new BigInteger("1").shiftLeft(i);
		//System.out.println(t.and(bitmask));
                if (t.and(bitmask).signum() != 0)
                {
                    v[i] += 1;
                }
                else
                {
                    v[i] -= 1;
                }
            }
        }
        BigInteger fingerprint = new BigInteger("0");
        for (int i = 0; i < hashbits; i++)
        {
            if (v[i] >= 0)
            {
                fingerprint = fingerprint.add(new BigInteger("1").shiftLeft(i));
            }
        }
        return fingerprint;
    }

    private BigInteger hash(String source)
    {
        if (source == null || source.length() == 0)
        {
            return new BigInteger("0");
        }
        else
        {
            char[] sourceArray = source.toCharArray();
            BigInteger x = BigInteger.valueOf(((long) sourceArray[0]) << 7);
            BigInteger m = new BigInteger("1000003");
            BigInteger mask = new BigInteger("2").pow(this.hashbits).subtract(
                new BigInteger("1"));
            for (char item : sourceArray)
            {
                BigInteger temp = BigInteger.valueOf((long) item);
                x = x.multiply(m).xor(temp).and(mask);
            }
            x = x.xor(new BigInteger(String.valueOf(source.length())));
            if (x.equals(new BigInteger("-1")))
            {
                x = new BigInteger("-2");
            }
            return x;
        }
    }

    public int hammingDistance(String doc1, String doc2 )
    {
        BigInteger m = new BigInteger("1").shiftLeft(hashbits).subtract(
            new BigInteger("1"));
        BigInteger x = simHash(doc1).xor(simHash(doc2)).and(m);
        int tot = 0;
        while (x.signum() != 0)
        {
            tot += 1;
            x = x.and(x.subtract(new BigInteger("1")));
        }
        return tot;
    }
}
