package moe.feng.support.biometricprompt;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

@RestrictTo({RestrictTo.Scope.LIBRARY})
class BiometricPromptCompatDialog extends Dialog {

    private final TextView mSubtitle, mDescription, mStatus;
    private final Button mNegativeButton;
    private final FingerprintIconView mFingerprintIcon;

    BiometricPromptCompatDialog(@NonNull Context context) {
        super(context, findThemeResId(context));

        setCancelable(true);
        setCanceledOnTouchOutside(true);

        Window window = getWindow();
        window.setLayout(MATCH_PARENT, WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM);

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setNavigationBarColor(context.getResources().getColor(android.R.color.white));

        View rootView = LayoutInflater.from(getContext())
                .inflate(R.layout.biometric_prompt_dialog_content, null);
        mSubtitle = rootView.findViewById(R.id.subtitle);
        mDescription = rootView.findViewById(R.id.description);
        mStatus = rootView.findViewById(R.id.status);
        mNegativeButton = rootView.findViewById(android.R.id.button1);
        mFingerprintIcon = rootView.findViewById(R.id.fingerprint_icon);
        rootView.setPadding(0, 0, 0, getNavigationBarHeight(context));
        addContentView(rootView, new ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
    }

    private static int findThemeResId(Context context) {
        TypedArray ta = context.obtainStyledAttributes(new int[]{R.attr.biometricPromptDialogTheme});
        int resId = ta.getResourceId(0, R.style.Theme_BiometricPromptDialog);
        ta.recycle();
        return resId;
    }

    private int getNavigationBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public TextView getSubtitle() {
        return mSubtitle;
    }

    public TextView getDescription() {
        return mDescription;
    }

    public TextView getStatus() {
        return mStatus;
    }

    public Button getNegativeButton() {
        return mNegativeButton;
    }

    public FingerprintIconView getFingerprintIcon() {
        return mFingerprintIcon;
    }

}
