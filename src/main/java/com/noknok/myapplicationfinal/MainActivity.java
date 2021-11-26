package com.noknok.myapplicationfinal;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.simpleButton);
        EditText edInputString = findViewById(R.id.et_str_to_swap);
        TextView tvSortedString = findViewById(R.id.tv_swapped_str);

        button.setOnClickListener(v -> {
            int[] array = convertToArray(edInputString.getText().toString());

            sort(array);
            if (isSorted(array)) {
                tvSortedString.setText(Arrays.toString(array));
            }

        });
    }

    /**
     * Converts the input String to array of integers
     *
     * @param edInputString is the input
     */
    private int[] convertToArray(String edInputString) {
        int[] array = new int[edInputString.length()];

        for (int i = 0; i < edInputString.length(); i++) {
            array[i] = Integer.parseInt(String.valueOf(edInputString.charAt(i)));
        }
        return array;
    }

    /**
     * Checks the size of the array
     * if the size is equal to 5
     * if the size is greater than 5,during the sorting process shortens and brings to 5 if possible
     * Sorts the array
     *
     * @param array is the input
     */
    private void sort(int[] array) {
        int lastIndex = array.length - 1;
        int i;
        if (array.length > 5) {
            //int a=array.length-1;
            for (i = 1; i <= lastIndex - 1; i++) {
                    Log.i(TAG, "" + i + " " + lastIndex + " " + Arrays.toString(array));

                if (i + 1 == lastIndex) {
                    i = 0;
                }

                if (array[lastIndex] == 1) {
                    lastIndex = lastIndex - 1;
                } else { //array[a]='0'
                    if (array[i] == 1 && i > 0) {
                        swap(array, i - 1, lastIndex - 1);
                        lastIndex = lastIndex - 1;
                    }
                }

                if (isSorted(array)) {
                    Log.i(TAG, "swapped " + Arrays.toString(array));
                    Log.i(TAG, "Sorting process is over !!");
                    return;
                }else if(i == lastIndex -1){
                    i = 0;
                }

                if (lastIndex == 4) {
                    break;
                }
            }
        }

        if (array.length == 5 || lastIndex == 4) {
            for (i = 0; i <= lastIndex; i++) {
                // System.out.println(Arrays.toString(array));
                Log.i(TAG, "Step " + Arrays.toString(array));

                if (i == lastIndex - 1) {
                    if (array[i - 1] == 1 && array[i] == 0 && array[i + 1] == 1) {
                        swap(array, i, i - 3);
                        i = -1;
                    } else if (array[i] == 1 && array[i + 1] == 0) {
                        swap(array, i - 1, i - 3);
                    } else if (array[i] == 0 && array[i + 1] == 1) {
                        swap(array, i - 1, i - 3);
                    } else if (array[i] == 0 && array[i + 1] == 0) {
                        swap(array, i, i - 3);
                    } else if (array[i] == 1 && array[i + 1] == 1) {
                        swap(array, i, i - 2);
                    } else {
                        swap(array, i + 1, i - 3);
                    }
                } else if (i == lastIndex) {
                    if (array[i - 1] == 0 && array[i] == 1) {
                        swap(array, i - 1, i - 4);
                        i = -1;
                    } else if (array[i - 1] == 1 && array[i] == 0) {
                        swap(array, i - 1, i - 3);
                        i = -1;
                    } else {
                        swap(array, i - 2, i - 4);
                    }
                } else {
                    if (array[i] == 0 && array[i + 1] == 1) {
                        if (i + 1 == lastIndex) {
                            if (array[i + 1] == 1) {
                                swap(array, i - 1, i - 3);
                            } else if (array[i + 1] == 0) {
                                swap(array, i, i - 3);
                            }
                        } else {
                            if (array[i + 2] == 0) {
                                if (i + 1 == lastIndex - 2) {
                                    swap(array, i, i + 2);
                                } else if (i + 1 == lastIndex - 3) {
                                    swap(array, i, lastIndex - 1);
                                } else if (i + 1 == lastIndex - 1 && array[i - 1] == 0) {
                                    swap(array, i - 1, i + 1);
                                } else if (i + 1 == lastIndex - 1) {
                                    swap(array, i + 1, i - 2);
                                } else {
                                    swap(array, i, i - 2);
                                }
                            }
                            if (array[i + 2] == 1) {
                                if (i + 1 == lastIndex - 1) {
                                    swap(array, i, i - 2);
                                } else if (i + 2 == lastIndex) {
                                    swap(array, i + 1, i - 2);
                                } else if (i + 2 == lastIndex - 2 && array[i + 3] == 1) {
                                    swap(array, i + 1, lastIndex - 1);
                                } else if (i + 2 == lastIndex - 1) {
                                    swap(array, i + 1, i - 1);
                                } else {
                                    swap(array, i + 1, lastIndex - 1);
                                }
                            }
                        }
                    }
                }
                if (isSorted(array)) {
                    Log.i(TAG, "swapped! " + Arrays.toString(array));
                    return;
                }
            }
        }
    }


    /**
     * @argument array is
     */
    private void swap(int[] array, int firsPair, int secondPair) {
        int temp1 = array[firsPair];
        int temp2 = array[firsPair + 1];
        array[firsPair] = array[secondPair];
        array[firsPair + 1] = array[secondPair + 1];
        array[secondPair] = temp1;
        array[secondPair + 1] = temp2;
    }


    /**
     * Intended for checking if array is sorted or not
     *
     * @return true if array is sorted otherwise false
     * @argument array
     */
    private boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }
}





