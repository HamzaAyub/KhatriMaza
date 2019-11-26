
package pk.getsub.latahitsongs.json;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SquareImageView extends android.support.v7.widget.AppCompatImageView
{

    public SquareImageView(Context context)
    {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public SquareImageView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        setMeasuredDimension(getMeasuredWidth(), (int) ((int) (getMeasuredWidth())/1.7));
    }
}
