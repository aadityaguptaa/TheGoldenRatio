package com.example.thegoldenratio;

public class Multi implements Runnable {

    TypeWriter typeWriter;

    public Multi(TypeWriter typeWriter){
        this.typeWriter = typeWriter;
    }

    @Override
    public void run() {
        Utils.delay(3, () -> {
            typeWriter.animateText("The Divine proportion doesn't even spare Saturn");
            Utils.delay(6, ()  -> {
                typeWriter.animateText("Can you find it??");
                Utils.delay(4, () -> {
                    typeWriter.animateText("Drag the image from the top left corner and try to fit it in");
                    Utils.delay(8, () -> {
                        typeWriter.animateText("Click on the question mark to see the answer :)");
                    });
                });
            });
        });
    }
}
