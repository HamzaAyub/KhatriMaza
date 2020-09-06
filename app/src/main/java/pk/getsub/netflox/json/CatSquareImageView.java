
package pk.getsub.netflox.json;

import android.content.Context;
import android.util.AttributeSet;

public class CatSquareImageView extends android.support.v7.widget.AppCompatImageView
{

    public CatSquareImageView(Context context)
    {
        super(context);
    }

    public CatSquareImageView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public CatSquareImageView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        setMeasuredDimension(getMeasuredWidth(), (int) ((int) (getMeasuredWidth())/1.1));
    }
}
