package com.github.mikephil.charting.animation;

import com.google.android.flexbox.FlexItem;

public class Easing {

    private static class EasingFunctions {
        public static final EasingFunction EaseInBack = new EasingFunction() {
            public float getInterpolation(float input) {
                float position = input;
                return (position * position) * ((2.70158f * position) - 1.70158f);
            }
        };
        public static final EasingFunction EaseInBounce = new EasingFunction() {
            public float getInterpolation(float input) {
                return FlexItem.FLEX_SHRINK_DEFAULT - EasingFunctions.EaseOutBounce.getInterpolation(FlexItem.FLEX_SHRINK_DEFAULT - input);
            }
        };
        public static final EasingFunction EaseInCirc = new EasingFunction() {
            public float getInterpolation(float input) {
                return -(((float) Math.sqrt((double) (FlexItem.FLEX_SHRINK_DEFAULT - (input * input)))) - FlexItem.FLEX_SHRINK_DEFAULT);
            }
        };
        public static final EasingFunction EaseInCubic = new C14865();
        public static final EasingFunction EaseInElastic = new EasingFunction() {
            public float getInterpolation(float input) {
                if (input == 0.0f) {
                    return 0.0f;
                }
                float position = input;
                if (position == FlexItem.FLEX_SHRINK_DEFAULT) {
                    return FlexItem.FLEX_SHRINK_DEFAULT;
                }
                position -= FlexItem.FLEX_SHRINK_DEFAULT;
                return -(((float) Math.pow(2.0d, (double) (10.0f * position))) * ((float) Math.sin((((double) (position - ((0.3f / 6.2831855f) * ((float) Math.asin(1.0d))))) * 6.283185307179586d) / ((double) 1050253722))));
            }
        };
        public static final EasingFunction EaseInExpo = new EasingFunction() {
            public float getInterpolation(float input) {
                return input == 0.0f ? 0.0f : (float) Math.pow(2.0d, (double) (10.0f * (input - FlexItem.FLEX_SHRINK_DEFAULT)));
            }
        };
        public static final EasingFunction EaseInOutBack = new EasingFunction() {
            public float getInterpolation(float input) {
                float position = input / 0.5f;
                if (position < FlexItem.FLEX_SHRINK_DEFAULT) {
                    float s = 1.70158f * 1.525f;
                    return ((position * position) * (((FlexItem.FLEX_SHRINK_DEFAULT + s) * position) - s)) * 0.5f;
                }
                position -= 2.0f;
                s = 1.70158f * 1.525f;
                return (((position * position) * (((FlexItem.FLEX_SHRINK_DEFAULT + s) * position) + s)) + 2.0f) * 0.5f;
            }
        };
        public static final EasingFunction EaseInOutBounce = new EasingFunction() {
            public float getInterpolation(float input) {
                if (input < 0.5f) {
                    return EasingFunctions.EaseInBounce.getInterpolation(2.0f * input) * 0.5f;
                }
                return (EasingFunctions.EaseOutBounce.getInterpolation((2.0f * input) - FlexItem.FLEX_SHRINK_DEFAULT) * 0.5f) + 0.5f;
            }
        };
        public static final EasingFunction EaseInOutCirc = new EasingFunction() {
            public float getInterpolation(float input) {
                float position = input / 0.5f;
                if (position < FlexItem.FLEX_SHRINK_DEFAULT) {
                    return -0.5f * (((float) Math.sqrt((double) (FlexItem.FLEX_SHRINK_DEFAULT - (position * position)))) - FlexItem.FLEX_SHRINK_DEFAULT);
                }
                position -= 2.0f;
                return (((float) Math.sqrt((double) (FlexItem.FLEX_SHRINK_DEFAULT - (position * position)))) + FlexItem.FLEX_SHRINK_DEFAULT) * 0.5f;
            }
        };
        public static final EasingFunction EaseInOutCubic = new C14887();
        public static final EasingFunction EaseInOutElastic = new EasingFunction() {
            public float getInterpolation(float input) {
                if (input == 0.0f) {
                    return 0.0f;
                }
                float position = input / 0.5f;
                if (position == 2.0f) {
                    return FlexItem.FLEX_SHRINK_DEFAULT;
                }
                float s = (0.45000002f / 6.2831855f) * ((float) Math.asin(1.0d));
                if (position < FlexItem.FLEX_SHRINK_DEFAULT) {
                    position -= FlexItem.FLEX_SHRINK_DEFAULT;
                    return -0.5f * (((float) Math.sin((((double) ((FlexItem.FLEX_SHRINK_DEFAULT * position) - s)) * 6.283185307179586d) / ((double) 1055286887))) * ((float) Math.pow(2.0d, (double) (10.0f * position))));
                }
                position -= FlexItem.FLEX_SHRINK_DEFAULT;
                return ((((float) Math.pow(2.0d, (double) (-10.0f * position))) * ((float) Math.sin((((double) ((position * FlexItem.FLEX_SHRINK_DEFAULT) - s)) * 6.283185307179586d) / ((double) 1055286887)))) * 0.5f) + FlexItem.FLEX_SHRINK_DEFAULT;
            }
        };
        public static final EasingFunction EaseInOutExpo = new EasingFunction() {
            public float getInterpolation(float input) {
                if (input == 0.0f) {
                    return 0.0f;
                }
                if (input == FlexItem.FLEX_SHRINK_DEFAULT) {
                    return FlexItem.FLEX_SHRINK_DEFAULT;
                }
                float position = input / 0.5f;
                if (position < FlexItem.FLEX_SHRINK_DEFAULT) {
                    return ((float) Math.pow(2.0d, (double) (10.0f * (position - FlexItem.FLEX_SHRINK_DEFAULT)))) * 0.5f;
                }
                return ((-((float) Math.pow(2.0d, (double) (-10.0f * (position - FlexItem.FLEX_SHRINK_DEFAULT))))) + 2.0f) * 0.5f;
            }
        };
        public static final EasingFunction EaseInOutQuad = new C14854();
        public static final EasingFunction EaseInOutQuart = new EasingFunction() {
            public float getInterpolation(float input) {
                float position = input / 0.5f;
                if (position < FlexItem.FLEX_SHRINK_DEFAULT) {
                    return (((0.5f * position) * position) * position) * position;
                }
                position -= 2.0f;
                return -0.5f * ((((position * position) * position) * position) - 2.0f);
            }
        };
        public static final EasingFunction EaseInOutSine = new EasingFunction() {
            public float getInterpolation(float input) {
                return -0.5f * (((float) Math.cos(3.141592653589793d * ((double) input))) - FlexItem.FLEX_SHRINK_DEFAULT);
            }
        };
        public static final EasingFunction EaseInQuad = new C14832();
        public static final EasingFunction EaseInQuart = new C14898();
        public static final EasingFunction EaseInSine = new EasingFunction() {
            public float getInterpolation(float input) {
                return (-((float) Math.cos(((double) input) * 1.5707963267948966d))) + FlexItem.FLEX_SHRINK_DEFAULT;
            }
        };
        public static final EasingFunction EaseOutBack = new EasingFunction() {
            public float getInterpolation(float input) {
                float position = input - FlexItem.FLEX_SHRINK_DEFAULT;
                return ((position * position) * ((2.70158f * position) + 1.70158f)) + FlexItem.FLEX_SHRINK_DEFAULT;
            }
        };
        public static final EasingFunction EaseOutBounce = new EasingFunction() {
            public float getInterpolation(float input) {
                float position = input;
                if (position < 0.36363637f) {
                    return (7.5625f * position) * position;
                }
                if (position < 0.72727275f) {
                    position -= 0.54545456f;
                    return ((7.5625f * position) * position) + 0.75f;
                } else if (position < 0.90909094f) {
                    position -= 0.8181818f;
                    return ((7.5625f * position) * position) + 0.9375f;
                } else {
                    position -= 0.95454544f;
                    return ((7.5625f * position) * position) + 0.984375f;
                }
            }
        };
        public static final EasingFunction EaseOutCirc = new EasingFunction() {
            public float getInterpolation(float input) {
                input -= FlexItem.FLEX_SHRINK_DEFAULT;
                return (float) Math.sqrt((double) (FlexItem.FLEX_SHRINK_DEFAULT - (input * input)));
            }
        };
        public static final EasingFunction EaseOutCubic = new C14876();
        public static final EasingFunction EaseOutElastic = new EasingFunction() {
            public float getInterpolation(float input) {
                if (input == 0.0f) {
                    return 0.0f;
                }
                float position = input;
                if (position == FlexItem.FLEX_SHRINK_DEFAULT) {
                    return FlexItem.FLEX_SHRINK_DEFAULT;
                }
                return (((float) Math.pow(2.0d, (double) (-10.0f * position))) * ((float) Math.sin((((double) (position - ((0.3f / 6.2831855f) * ((float) Math.asin(1.0d))))) * 6.283185307179586d) / ((double) 1050253722)))) + FlexItem.FLEX_SHRINK_DEFAULT;
            }
        };
        public static final EasingFunction EaseOutExpo = new EasingFunction() {
            public float getInterpolation(float input) {
                return input == FlexItem.FLEX_SHRINK_DEFAULT ? FlexItem.FLEX_SHRINK_DEFAULT : -((float) Math.pow(2.0d, (double) ((FlexItem.FLEX_SHRINK_DEFAULT + input) * -10.0f)));
            }
        };
        public static final EasingFunction EaseOutQuad = new C14843();
        public static final EasingFunction EaseOutQuart = new C14909();
        public static final EasingFunction EaseOutSine = new EasingFunction() {
            public float getInterpolation(float input) {
                return (float) Math.sin(((double) input) * 1.5707963267948966d);
            }
        };
        public static final EasingFunction Linear = new C14821();

        static class C14821 implements EasingFunction {
            C14821() {
            }

            public float getInterpolation(float input) {
                return input;
            }
        }

        static class C14832 implements EasingFunction {
            C14832() {
            }

            public float getInterpolation(float input) {
                return input * input;
            }
        }

        static class C14843 implements EasingFunction {
            C14843() {
            }

            public float getInterpolation(float input) {
                return (-input) * (input - 2.0f);
            }
        }

        static class C14854 implements EasingFunction {
            C14854() {
            }

            public float getInterpolation(float input) {
                float position = input / 0.5f;
                if (position < FlexItem.FLEX_SHRINK_DEFAULT) {
                    return (0.5f * position) * position;
                }
                position -= FlexItem.FLEX_SHRINK_DEFAULT;
                return -0.5f * (((position - 2.0f) * position) - FlexItem.FLEX_SHRINK_DEFAULT);
            }
        }

        static class C14865 implements EasingFunction {
            C14865() {
            }

            public float getInterpolation(float input) {
                return (input * input) * input;
            }
        }

        static class C14876 implements EasingFunction {
            C14876() {
            }

            public float getInterpolation(float input) {
                input -= FlexItem.FLEX_SHRINK_DEFAULT;
                return ((input * input) * input) + FlexItem.FLEX_SHRINK_DEFAULT;
            }
        }

        static class C14887 implements EasingFunction {
            C14887() {
            }

            public float getInterpolation(float input) {
                float position = input / 0.5f;
                if (position < FlexItem.FLEX_SHRINK_DEFAULT) {
                    return ((0.5f * position) * position) * position;
                }
                position -= 2.0f;
                return (((position * position) * position) + 2.0f) * 0.5f;
            }
        }

        static class C14898 implements EasingFunction {
            C14898() {
            }

            public float getInterpolation(float input) {
                return ((input * input) * input) * input;
            }
        }

        static class C14909 implements EasingFunction {
            C14909() {
            }

            public float getInterpolation(float input) {
                input -= FlexItem.FLEX_SHRINK_DEFAULT;
                return -((((input * input) * input) * input) - FlexItem.FLEX_SHRINK_DEFAULT);
            }
        }

        private EasingFunctions() {
        }
    }

    public enum EasingOption {
        Linear,
        EaseInQuad,
        EaseOutQuad,
        EaseInOutQuad,
        EaseInCubic,
        EaseOutCubic,
        EaseInOutCubic,
        EaseInQuart,
        EaseOutQuart,
        EaseInOutQuart,
        EaseInSine,
        EaseOutSine,
        EaseInOutSine,
        EaseInExpo,
        EaseOutExpo,
        EaseInOutExpo,
        EaseInCirc,
        EaseOutCirc,
        EaseInOutCirc,
        EaseInElastic,
        EaseOutElastic,
        EaseInOutElastic,
        EaseInBack,
        EaseOutBack,
        EaseInOutBack,
        EaseInBounce,
        EaseOutBounce,
        EaseInOutBounce
    }

    public static EasingFunction getEasingFunctionFromOption(EasingOption easing) {
        switch (easing) {
            case EaseInQuad:
                return EasingFunctions.EaseInQuad;
            case EaseOutQuad:
                return EasingFunctions.EaseOutQuad;
            case EaseInOutQuad:
                return EasingFunctions.EaseInOutQuad;
            case EaseInCubic:
                return EasingFunctions.EaseInCubic;
            case EaseOutCubic:
                return EasingFunctions.EaseOutCubic;
            case EaseInOutCubic:
                return EasingFunctions.EaseInOutCubic;
            case EaseInQuart:
                return EasingFunctions.EaseInQuart;
            case EaseOutQuart:
                return EasingFunctions.EaseOutQuart;
            case EaseInOutQuart:
                return EasingFunctions.EaseInOutQuart;
            case EaseInSine:
                return EasingFunctions.EaseInSine;
            case EaseOutSine:
                return EasingFunctions.EaseOutSine;
            case EaseInOutSine:
                return EasingFunctions.EaseInOutSine;
            case EaseInExpo:
                return EasingFunctions.EaseInExpo;
            case EaseOutExpo:
                return EasingFunctions.EaseOutExpo;
            case EaseInOutExpo:
                return EasingFunctions.EaseInOutExpo;
            case EaseInCirc:
                return EasingFunctions.EaseInCirc;
            case EaseOutCirc:
                return EasingFunctions.EaseOutCirc;
            case EaseInOutCirc:
                return EasingFunctions.EaseInOutCirc;
            case EaseInElastic:
                return EasingFunctions.EaseInElastic;
            case EaseOutElastic:
                return EasingFunctions.EaseOutElastic;
            case EaseInOutElastic:
                return EasingFunctions.EaseInOutElastic;
            case EaseInBack:
                return EasingFunctions.EaseInBack;
            case EaseOutBack:
                return EasingFunctions.EaseOutBack;
            case EaseInOutBack:
                return EasingFunctions.EaseInOutBack;
            case EaseInBounce:
                return EasingFunctions.EaseInBounce;
            case EaseOutBounce:
                return EasingFunctions.EaseOutBounce;
            case EaseInOutBounce:
                return EasingFunctions.EaseInOutBounce;
            default:
                return EasingFunctions.Linear;
        }
    }
}
