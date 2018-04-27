package com.svenskoglund.synth;

public class Config {
    
    /**
     * The Raspberry Pi model executing the framework.
     * 
     * <p>The default value for this Raspberry Pi model is the Raspberry Pi 2.</p>
     * 
     * @see Config#getRaspberryPiModel()
     * @see Config#setRaspberryPiModel(RaspberryPiModel)
     */
    private static RaspberryPiModel raspberryPiModel = RaspberryPiModel.PI2;
    
    /**
     * Private constructor to hide the implicit public one.
     */
    private Config() {
    }
    
    /**
     * This method allows you to retrieve the selected Raspberry Pi model executing the program.
     * @return the selected Raspberry Pi model on which the program is executing.
     */
    public static RaspberryPiModel getRaspberryPiModel() {
        return raspberryPiModel;
    }
    
    /**
     * This method allows you to set the selected Raspberry Pi model executing the program.
     * @param model the selected Raspberry Pi model.
     */
    public static void setRaspberryPiModel(RaspberryPiModel model) {
        raspberryPiModel = model;
    }
}
