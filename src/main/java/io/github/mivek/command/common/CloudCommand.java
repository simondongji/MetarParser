package io.github.mivek.command.common;

import io.github.mivek.enums.CloudQuantity;
import io.github.mivek.enums.CloudType;
import io.github.mivek.model.AbstractWeatherContainer;
import io.github.mivek.model.Cloud;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class CloudCommand implements Command {
    /** Pattern to recognize clouds. */
    private static final Pattern CLOUD_REGEX = Pattern.compile("^([A-Z]{3})(\\d{3})?([A-Z]{2,3})?$");

    @Override public boolean execute(final AbstractWeatherContainer pContainer, final String pPart) {
        Cloud c = parseCloud(pPart);
        return pContainer.addCloud(c);
    }

    @Override
    public boolean getReturnValue() {
        return true;
    }

    /**
     * This method parses the cloud part of the metar.
     *
     * @param pCloudString string with cloud elements.
     * @return a decoded cloud with its quantity, its altitude and its type.
     */
    protected Cloud parseCloud(final String pCloudString) {
        Cloud cloud = new Cloud();
        String[] cloudPart = Regex.pregMatch(CLOUD_REGEX, pCloudString);
        try {
            CloudQuantity cq = CloudQuantity.valueOf(cloudPart[1]);
            cloud.setQuantity(cq);
            if (cloudPart[2] != null) {
                cloud.setHeight(100 * Integer.parseInt(cloudPart[2]));
                if (cloudPart[3] != null) {
                    CloudType ct = CloudType.valueOf(cloudPart[3]);
                    cloud.setType(ct);
                }
            }
            return cloud;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(CLOUD_REGEX, pInput);
    }
}

