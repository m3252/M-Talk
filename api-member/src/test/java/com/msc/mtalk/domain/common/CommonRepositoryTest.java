package com.msc.mtalk.domain.common;

import com.msc.mtalk.config.JpaAuditConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(JpaAuditConfig.class)
public abstract class CommonRepositoryTest {

    @Autowired
    protected TestEntityManager em;
}
